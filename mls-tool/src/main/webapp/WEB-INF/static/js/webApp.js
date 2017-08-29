function initWebApp(app) {
  app
      .controller(
          'webController',
          [
              '$scope',
              '$http',
              '$uibModal',
              '$log',
              function($scope, $http, $uibModal, $log) {
                $scope.selectedItem = [];
                $scope.leftSeltected = [];
                $scope.resources = getResources(mls_meta);
                $scope.classes;
                $scope.samples;
                $scope.dateSource;
                $scope.parseMLS = [];

                // 全局监视器;
                $scope.selectedResource = $scope.resources[0];
                $scope.$watch('selectedResource', function(newValue, oldValue) {
                  var temp = [];

                  for ( var key in mls_meta[newValue]) {
                    temp.push(key);
                  }
                  $scope.classes = temp;
                  $scope.selectedClass = $scope.classes[0];
                });
                $scope.$watch('selectedSample', function(newValue, oldValue) {
                  setSampleData(newValue);
                });

                $scope.update = function(size) {
                  var leftScope = angular.element(leftGridDiv).scope();
                  $scope.leftSeltected = leftScope.gridApi.selection
                      .getSelectedRows();
                  if ($scope.leftSeltected.length < 1) {
                    alert("请选择listing_info_full字段");
                    return false;
                  }
                  var modalInstance = $uibModal.open({
                    templateUrl : 'myModalContent.html',
                    controller : 'ModalInstanceCtrl',
                    backdrop : "static",
                    size : size,
                    resolve : {
                      items1 : function() {
                        return $scope.leftSeltected[0].js;
                      }
                    }

                  });

                  modalInstance.result.then(function(item) {
                    $scope.leftSeltected[0].js = item;
                  }, function() {
                    $log.info('Modal dismissed at: ' + new Date());
                  });

                  $scope.toggleAnimation = function() {
                    $scope.animationsEnabled = !$scope.animationsEnabled;
                  };
                };

                // 增
                $scope.addElem = function() {
                  var leftScope = angular.element(leftGridDiv).scope();
                  $scope.leftSeltected = leftScope.gridApi.selection
                      .getSelectedRows();
                  if ($scope.leftSeltected.length < 1) {
                    alert("请选择listing_info_full字段");
                    return false;
                  } else {
                    var mlsData = "";
                    var scope = angular.element(mlsGridDiv).scope();
                    console.log(scope.gridApi.selection.getSelectedRows());
                    angular.forEach(scope.gridApi.selection.getSelectedRows(),
                        function(value, key) {// $scope.selectedItem
                          mlsData = mlsData + value.colname + ",";
                        });
                    if ($scope.selectedClass != "COMMONFIELD") {
                      $scope.leftSeltected[0].mlsCols = mlsData.substring(0,
                          mlsData.length - 1);
                    } else {
                      var selColName = $scope.leftSeltected[0].columnName;
                      for ( var c in lcolJSON[$scope.selectedResource]) {
                        angular.forEach(
                            lcolJSON[$scope.selectedResource][c]["data"],
                            function(value, key) {
                              if (value.columnName == selColName
                                  && value.mlsCols == "") {
                                value.mlsCols = mlsData.substring(0,
                                    mlsData.length - 1);
                                value.js = $scope.leftSeltected[0].js;
                              }
                            });
                      }
                    }
                  }
                };
                // 撤销
                $scope.repeal = function() {
                  var leftScope = angular.element(leftGridDiv).scope();
                  $scope.leftSeltected = leftScope.gridApi.selection
                      .getSelectedRows();
                  if ($scope.leftSeltected.length < 1) {
                    alert("请选择listing_info_full字段");
                    return false;
                  } else {
                    if ($scope.selectedClass != "COMMONFIELD") {
                      $scope.leftSeltected[0].mlsCols = "";
                      $scope.leftSeltected[0].js = "";
                    } else {
                      var selColName = $scope.leftSeltected[0].columnName;
                      var selMlsCols = $scope.leftSeltected[0].mlsCols;
                      for ( var c in lcolJSON[$scope.selectedResource]) {
                        angular.forEach(
                            lcolJSON[$scope.selectedResource][c]["data"],
                            function(value, key) {
                              if (value.columnName == selColName
                                  && selMlsCols == value.mlsCols) {
                                value.mlsCols = "";
                                value.js = "";
                              }
                            });
                      }
                    }
                  }
                };
                // 重置
                $scope.reset = function() {
                  angular.forEach($scope.dateSource, function(value, key) {
                    value.mlsCols = null;
                    value.js = null;
                  });
                };
                // 删除函数
                $scope.removeFuncs = function() {
                  $scope.leftSeltected[0].js = null;
                };

                // 每个class选择后的一次保存
                $scope.saveData = function() {
                  var dataMLS = new Object();
                  dataMLS = angular
                      .copy(lcolJSON[$scope.selectedResource][$scope.selectedClass]["data"]);
                  $scope.parseMLS.push(dataMLS);
                };
                // 复制上一次class的数据
                $scope.copyData = function() {
                  var len;
                  if ((len = $scope.parseMLS.length) > 0) {
                    var newDataMLS = $scope.parseMLS.pop();
                    for (var i = 0; i < newDataMLS.length; i++) {
                      lcolJSON[$scope.selectedResource][$scope.selectedClass]["data"][i]["mlsCols"] = newDataMLS[i]["mlsCols"];
                      lcolJSON[$scope.selectedResource][$scope.selectedClass]["data"][i]["js"] = newDataMLS[i]["js"];
                    }

                  } else {
                    alert("暂无数据可供粘贴！");
                  }
                };

                // 回传数据，写文件或数据库
                $scope.saveFile = function() {
                  var newDate = lcolJSON;
                  $.ajax({
                    type : 'POST',
                    contentType : "application/json",
                    url : '/mls-tool/save/' + mlsId,
                    dataType : "text",
                    data : JSON.stringify(newDate),
                    success : function(data) {
                      alert('创建成功！' + data);
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                      alert('创建失败！' + errorThrown);
                    }
                  });
                };

                $scope.download = function() {
                  var resource = $scope.selectedResource;
                  var classes = $scope.classes.join(";");
                  $.ajax({
                    type : 'GET',
                    contentType : "application/json",
                    url : '/mls-tool/saveResource?mlsId=' + mlsId
                        + '&resource=' + resource + '&classes=' + classes,
                    dataType : "text",
                    success : function(data) {
                      alert('创建成功！' + data);
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                      alert('创建失败！' + errorThrown);
                    }
                  });
                }
              } ]);
  // $uibModalInstance是模态窗口的实例
  app.controller('ModalInstanceCtrl', function($scope, $uibModalInstance,
      items1) {
    $scope.item = items1;
    $scope.ok = function() {
      $uibModalInstance.close($scope.item);
      var scope = angular.element(mainDiv).scope();
      if (scope.selectedClass == "COMMONFIELD") {
        for ( var c in lcolJSON[scope.selectedResource]) {
          angular.forEach(lcolJSON[scope.selectedResource][c]["data"],
              function(value, key) {
                if (value.columnName == leftSelected()[0].columnName
                    && value.js == "") {
                  value.js = $scope.item;
                }
              });
        }
      }
    };

    $scope.cancel = function() {
      $uibModalInstance.dismiss('cancel');
    };
  });
}