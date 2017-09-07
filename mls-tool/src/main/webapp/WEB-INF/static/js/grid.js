function init(app) {
  app
      .controller(
          'customersCtrl2',
          function($scope, i18nService) {
            i18nService.setCurrentLang('zh-cn');
            $scope.mlsdateSource;
            $scope
                .$watch(
                    'selectedClass',
                    function(newValue, oldValue) {
                      $scope.mlsdateSource = mls_meta[$scope.selectedResource][newValue]["dataDisplay"];
                      onClazzChanged();
                    });
            $scope.myDefs = [
                {
                  field : 'colname',
                  width : '160',
                  displayName : '字段名',
                  filter : {
                    condition : function(searchTerm, cellValue, row) {
                      var colname = row.entity.colname.toLowerCase().indexOf(
                          searchTerm.toLowerCase()) >= 0;
                      var stdname = row.entity.stdname.toLowerCase().indexOf(
                          searchTerm.toLowerCase()) >= 0;
                      var name = row.entity.name.toLowerCase().indexOf(
                          searchTerm.toLowerCase()) >= 0;
                      return colname | stdname | name;
                    }
                  },
                },
                {
                  field : 'stdname',
                  width : '160',
                  displayName : '字段全名'
                },
                {
                  field : 'type',
                  width : '100',
                  displayName : '类型'
                },
                {
                  field : 'name',
                  width : '200',
                  displayName : '描述',
                  cellTemplate : '<a title="{{row.entity.name}}">{{row.entity.name}}</a>'
                }, {
                  field : 'sample',
                  width : '150',
                  displayName : '样例数据'
                } ];
            $scope.rowFormatter = function(row) {
              return row.entity.comm == '1';
            };
            var rt = '<div ng-class="{ \'comm-css-class\': grid.appScope.rowFormatter( row ) }">'
                + '  <div ng-if="row.entity.merge">{{row.entity.title}}</div>'
                + '  <div ng-if="!row.entity.merge" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell></div>'
                + '</div>';
            $scope.mlsGrid = {
              data : 'mlsdateSource',
              columnDefs : $scope.myDefs,
              enableFiltering : true,
              enableGridMenu : true,
              onRegisterApi : function(gridApi) {
                $scope.gridApi = gridApi;
              },
              rowTemplate : rt
            }
          });
  app
      .controller(
          'customersCtrl',
          function($scope, i18nService) {
            i18nService.setCurrentLang('zh-cn');
            $scope
                .$watch(
                    'selectedClass',
                    function(newValue, oldValue) {
                      var scope = angular.element(mainDiv).scope();
                      scope.dateSource = lcolJSON[$scope.selectedResource][newValue]["data"];
                    });
            $scope.myDefs = [
                {
                  field : 'columnName',
                  enableCellEdit : false,
                  width : '150',
                  displayName : '字段名'
                },
                {
                  field : 'columnComment',
                  enableCellEdit : false,
                  width : '150',
                  displayName : '描述',
                  cellTemplate : '<a title="{{row.entity.columnComment}}">{{row.entity.columnComment}}</a>'
                },
                {
                  field : 'dataType',
                  width : '60',
                  enableCellEdit : false,
                  displayName : '字段类型',
                  visible : false
                },
                {
                  field : 'mlsCols',
                  width : '160',
                  enableCellEdit : true,
                  displayName : '匹配字段',
                  cellTemplate : '<a title="{{row.entity.mlsCols}}">{{row.entity.mlsCols}}</a>'
                }, {
                  field : 'sample',
                  width : '120',
                  displayName : '解析结果',
                  enableCellEdit : false
                }, {
                  field : 'js',
                  enableCellEdit : true,
                  width : '320',
                  displayName : '解析函数'
                } ];
            $scope.gridOptions = {
              data : 'dateSource',// 数据
              columnDefs : $scope.myDefs,// 表头
              enableFiltering : true,
              enableGridMenu : true,
              multiSelect : false,
              onRegisterApi : function(gridApi) {
                $scope.gridApi = gridApi;
                $scope.gridApi.selection.on.rowSelectionChanged($scope,
                    function(row, event) {
                      var scope = angular.element(mlsGridDiv).scope();
                      scope.gridApi.selection.clearSelectedRows();
                    });
                $scope.gridApi.edit.on.afterCellEdit($scope,
                    function(rowEntity) {
                      var scope = angular.element(mainDiv).scope();
                      if (scope.selectedClass == "COMMONFIELD") {
                        for ( var c in lcolJSON[scope.selectedResource]) {
                          angular.forEach(
                              lcolJSON[scope.selectedResource][c]["data"],
                              function(value, key) {
                                if (value.columnName == rowEntity.columnName
                                    && value.js == "") {
                                  value.mlsCols = rowEntity.mlsCols;
                                  value.js = rowEntity.js;
                                }
                              });
                        }
                      }
                    });
              }
            }
          });
}