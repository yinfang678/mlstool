<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid.css">
<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<script src="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid.debug.js"></script>
<script
	src="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid-flexible-height.js"></script>
<script
    src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
<title>MLS TOOL</title>
<style type="text/css">
    h4 {
        margin-top: 25px;
    }
    .row {
        margin-bottom: 20px;
    }
    .row .row {
        margin-top: 10px;
        margin-bottom: 0;
    }

    hr {
        margin-top: 40px;
        margin-bottom: 40px;
    }
    .title {
        text-align: center;
    }
    .ngViewport {
        width: auto !important;
        overflow-x: scroll;
    }
</style>
</head>
<body ng-app="myApp">
	<div class="container-full" ng-controller="webController">
		<div class="page-header">
			<h1 class="title">房源解析开发环境</h1>
			<%--<p class="lead">副标题</p>--%>
		</div>
        <!-- Transform Function 下拉框 -->
        <div class="row" style ="margin-top:-10px;">
            <div class="col-md-6">
                <div class="panel-heading">
                    Transform Function:
                    <select class="func selectpicker" multiple>
                        <option>func1</option>
                        <option>func2</option>
                        <option>func3</option>
                    </select>
                </div>
            </div>
            <%--<div class="col-md-3">--%>
                <%--<div class="panel-heading" style="height: 55px; display: table-cell;">--%>
                    <%--Resources:--%>
                    <%--<select ng-model="selectedResource">--%>
                        <%--<option ng-repeat="x in resources">{{x}}</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="col-md-3">
                <div class="panel-heading" style="height: 55px; display: table-cell;">
                    CLASS:
                    <select ng-model="selectedClass">
                        <option ng-repeat="x in classes">{{x}}</option>
                    </select>
                </div>
            </div>

        </div>
        <%-- 字段比对列表 --%>
		<div class="row" >
			<div class="col-md-6" ng-controller="customersCtrl">
				<div class="panel panel-default">
					<div class="panel-heading title">listing_info_full表字段</div>
					<div class="panel-body " ng-grid="gridOptions" style="height: 480px; "></div>
				</div>
			</div>
            <div class="col-md-1" style="text-align: center; margin-top: 200px">
                <div class="btn-group btn-group-vertical" role="group" aria-label="...">
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-primary" ng-click="addElem()">添加信息</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-info" ng-click="repeal()">删除信息</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-warning" ng-click="reset()">重置</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default" ng-click="saveData()">保存CLASS</button>
                    </div>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default" ng-click="copyData()">复制CLASS</button>
                    </div>

                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-default" ng-click="saveFile()">存储</button>
                    </div>
                    <div>
                        <button type="button" disabled class="btn btn-default" ng-click="show()">显示</button>
                    </div>
                </div>
            </div>
			<div class="col-md-5" ng-controller="customersCtrl2">
				<div class="panel panel-default">
					<div class="panel-heading title">MLS字段</div>
					<div class="panel-body" ng-grid="mlsGrid" style="height: 480px">
					</div>
				</div>
			</div>
		</div>



            <%--<div class="col-lg-6">--%>
                <%--<div class="input-group">--%>
			    <%--<span class="input-group-btn">--%>
			        <%--<button class="btn btn-default disabled" type="button">Class</button>--%>
			    <%--</span>--%>
                    <%--<select class="selectpicker form-control" data-live-search="true">--%>
                        <%--<option>Hot Dog, Fries and a Soda</option>--%>
                        <%--<option>Burger, Shake and a Smile</option>--%>
                        <%--<option>Sugar, Spice and all things nice</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>

        <%----%>
        <%--<div class="row">--%>
            <%--<div class="col-md-12">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading title">ParseJSON</div>--%>
                    <%--<div class="panel-body" ng-grid="mls">--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
	</div>

	<script>
		var app = angular.module('myApp', [ 'ngGrid' ]);
		app.value("selectedItems", []);
		var lcolStr = '${listing_cols}';
		var classes = eval("(" + '${classes}' + ")");
		var mls_cols = JSON.parse('${mls_cols}');
		<%--var mls_meta = JSON.parse('${mls_meta}');--%>
		var lcolJSON = JSON.parse(lcolStr);
		app.config(['$provide', function($provide){
            $provide.decorator('$rootScope', ['$delegate', function($delegate){
                Object.defineProperty($delegate.constructor.prototype, '$onRootScope', {
                    value: function(name, listener){
                        var unsubscribe = $delegate.$on(name, listener);
                        this.$on('$destroy', unsubscribe);
                        return unsubscribe;
                    },
                    enumerable: false
                });
                return $delegate;
            }]);
        }]);
        function getResources(meta) {
            var resorces = {};
            $.each(meta, function (key, data) {
               resorces[data.key] = data.value;
            });
        }

        function getIndexes(meta) {
          var indexes = [];
          for (var i=0; i < meta.length-1; i++){
              indexes[i] = i;
          }
          return indexes;
        }
		app.controller('webController', ['$scope','$http', function ($scope, $http) {
            $scope.selectedItem = [];
            $scope.leftSeltected = [];
			$scope.dateSource = lcolJSON;
//			$scope.indexes = getIndexes(mls_meta);
//			$scope.resources;
            $scope.classes = classes;
            $scope.parseMLS = [];

//          全局监视器;
//            $scope.$watch('selectIndex', function(newValue, oldValue) {
//                var temp = [];
//                for (var key in mls_meta[newValue]) {
//                    temp.push(key);
//                }
//                $scope.resources = temp;
//            });
//            $scope.$watch('selectedResource', function(newValue, oldValue) {
//                var temp = [];
//
//                for (var key in mls_meta[$scope.selectIndex][newValue]) {
//                    temp.push(key);
//                }
//
//                $scope.classes = temp;
//            });

//          增
            $scope.addElem = function () {
                if ($scope.leftSeltected.length < 1) {
                    alert("请选择listing_info_full字段");
                    return false;
                } else {
                    $scope.leftSeltected[0].mlsCols = $scope.selectedItem;
                    $scope.addFuncs();
                    console.log($scope.leftSeltected);
                }
            };
//          撤销
            $scope.repeal = function () {
                if ($scope.leftSeltected.length < 1) {
                    alert("请选择listing_info_full字段");
                    return false;
                } else {
                    $scope.leftSeltected[0].mlsCols = null;
                }
                $scope.removeFuncs();
            };
//          重置
            $scope.reset = function () {
                angular.forEach($scope.dateSource, function(value, key) {
                    value.mlsCols = null;
                    value.transform = null;
                });
            };
//          增加函数
            $scope.addFuncs = function () {
                var s = "";
                $('.func option:selected').each(function (key, value) {
                    s = s + $(value).text() + ",";
                });
//                alert(s);
                $scope.leftSeltected[0].transform = s.substring(0, s.length - 1);
            };
//          删除函数
            $scope.removeFuncs = function () {
                $scope.leftSeltected[0].transform = null;
            };
            
//          每个class选择后的一次保存
            $scope.saveData = function () {
                var classMLS = new Object();
                classMLS.class = $scope.selectedClass;
                classMLS.meta = $scope.dateSource;
                $scope.parseMLS.push(classMLS);
            };
//          复制上一次class的结构
            $scope.copyData = function () {
                var len;
                if ((len=$scope.parseMLS.length) > 0) {
                    var newClassMLS = new Object();
                    newClassMLS.class = $scope.selectedClass;
                    newClassMLS.meta = $scope.parseMLS[len-1].meta;
                    $scope.parseMLS.push(newClassMLS);
                } else {
                    alert("暂无数据可供复制！");
                }
            };
            $scope.show = function () {
                angular.forEach($scope.parseMLS, function(value, key) {
                    var rclass = value.class;
                    var metaData = value.meta;
                    $scope.myDefs = [ {
                        field : 'columnName',
                        //				width : '25%',
                        displayName : '字段名'
                    }, {
                        field : 'columnComment',
                        displayName : '描述'
                    }, {
                        field : 'dataType',
                        //				width : '20%',
                        displayName : '字段类型'
                    }, {
                        field: 'mlsCols',
                        displayName: '匹配字段'
                    }, {
                        field: 'transform',
                        displayName: '方法名'
                    } ];
                    $scope.mls = {
                        i18n : 'zh-cn',
                        data : 'metaData',// 数据
                        columnDefs : $scope.myDefs,// 表头
                        selectedItems : $scope.leftSeltected,//选择某行这一行的对象就赋值给$scope.selectedItem
                        multiSelect : false,//不可多选
                        showSelectionCheckbox : true,
                        showGroupPanel: true
                    }
                });
            };
//          回传数据，写文件或数据库
            $scope.saveFile = function(){
            var newDate = $scope.parseMLS;
//                alert(newDate);
                $http({
                    url:'/save',
                    method:'POST',
                    data:JSON.stringify(newDate)
                }).success(function(data){
                    $scope.msg = '创建成功！';
                }).error(function(data) {
                    $scope.msg = '创建失败！';
                });
            }
        }]);
		app.controller('customersCtrl', function($scope) {
//            $scope.selectedItem = [];
			$scope.myDefs = [ {
				field : 'columnName',
//				width : '25%',
				displayName : '字段名'
			}, {
				field : 'columnComment',
				displayName : '描述'
			}, {
				field : 'dataType',
//				width : '20%',
				displayName : '字段类型'
			}, {
			    field: 'mlsCols',
                displayName: '匹配字段'
            }, {
			    field: 'transform',
                displayName: '方法名'
            } ];
			$scope.gridOptions = {
				i18n : 'zh-cn',
				data : 'dateSource',// 数据
				columnDefs : $scope.myDefs,// 表头
				selectedItems : $scope.leftSeltected,//选择某行这一行的对象就赋值给$scope.selectedItem
				multiSelect : false,//不可多选
				showSelectionCheckbox : true
			}
		});
		app.controller('customersCtrl2', function($scope, $http){

			$scope.mlsdateSource;
            $scope.$watch('selectedClass', function(newValue, oldValue) {
				$scope.mlsdateSource = mls_cols[newValue];
			});
			$scope.myDefs = [ {
				field : 'name',
				width : '25%',
				displayName : '字段名'
			}, {
				field : 'desc',
				displayName : '描述'
			}, {
				field : 'type',
				width : '20%',
				displayName : '字段类型'
			} ];
			$scope.mlsGrid = {
				i18n : 'zh-cn',
				data : 'mlsdateSource',
				columnDefs : $scope.myDefs,
				selectedItems : $scope.selectedItem,//选择某行这一行的对象就赋值给$scope.selectedItem
				multiSelect : true,//可多选
				showSelectionCheckbox : true
			}
		});
//		$("#transform").click(function () {
////            $('.selectpicker option:selected').each(function (key, value) {
////                alert($(value).text());
////            });
//            alert($('.selectpicker option:selected').text());
//        });
        function syntaxHighlight(json) {
            if (typeof json != 'string') {
                json = JSON.stringify(json, undefined, 2);
            }
            return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
                var cls = 'number';
                if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                        cls = 'key';
                    } else {
                        cls = 'string';
                    }
                } else if (/true|false/.test(match)) {
                    cls = 'boolean';
                } else if (/null/.test(match)) {
                    cls = 'null';
                }
                return '<span class="' + cls + '">' + match + '</span>';
            });
        }
	</script>
</body>
</html>