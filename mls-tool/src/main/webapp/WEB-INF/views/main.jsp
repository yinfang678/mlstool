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
<link rel="stylesheet" type="text/css" href="static/css/ui-grid.css" />
<link rel="stylesheet" type="text/css" href="static/css/main.css" />
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>
<script src="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid.debug.js"></script>
<script
	src="https://cdn.bootcss.com/ng-grid/2.0.11/ng-grid-flexible-height.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="static/js/ui-grid.js"></script>
<title>MLS TOOL</title>
</head>
<body ng-app="myApp">
	<div class="container-full" ng-controller="webController">
		<div class="page-header">
			<h1 class="title">房源解析开发环境</h1>
			<%--<p class="lead">副标题</p>--%>
		</div>
		<div class="row">
			<div id="leftGridDiv" class="col-md-8" ng-controller="customersCtrl">
				<div class="panel panel-default">
					<div class="panel-heading title">房源列表</div>
					<div class="panel-body " ui-grid="gridOptions" ui-grid-selection
						ui-grid-resize-columns style="height: 480px;"></div>
				</div>
			</div>
			<div class="col-md-1" style="text-align: center; margin-top: 200px">
				<div class="btn-group btn-group-vertical" role="group"
					aria-label="...">
					<div class="btn-group" role="group">
						<p>
							mlsID : <input type="text" ng-model="mlsID">
						</p>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-primary" ng-click="addElem()">添加MLS</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default"
							ng-click="loadMeta()">获取META_DATA</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default"
							ng-click="loadSample()">下载样例数据</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-warning" ng-click="toPre()">同步到预发布</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-warning" ng-click="toOnline()">同步到线上</button>
					</div>
				</div>
			</div>
		</div>

	</div>

	<script>
    var app = angular.module('myApp', [ 'ui.grid', 'ui.grid.selection',
        'ui.grid.edit', 'ui.grid.resizeColumns', 'ui.grid.autoResize' ]);
    var mls_meta = JSON.parse('${mls_meta}');

    app.controller('webController', [
        '$scope',
        '$http',
        function($scope, $http) {
          $scope.dateSource = mls_meta;
          $scope.addElem = function() {
            $.ajax({
              type : 'POST',
              contentType : "application/json",
              url : '/mls-tool/insert/new-mls?mlsId=' + $scope.mlsID,
              dataType : 'text',
              success : function(data) {
                location.reload(true);
              },
              error : function(XMLHttpRequest, textStatus, errorThrown) {
                location.reload(true);
              }
            });
          };
          $scope.loadMeta = function() {
            alert("loadMeta");
          };
          $scope.loadSample = function() {
              $.ajax({
                type : 'GET',
                contentType : "application/json",
                url : '/mls-tool/saveResourceById?mlsId=' + mlsId,
                dataType : "text",
                success : function(data) {
                  alert('创建成功！' + data);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                  alert('创建失败！' + errorThrown);
                }
              });
          };
          $scope.toPre = function() {
            var leftScope = angular.element(leftGridDiv).scope();
            var sel = leftScope.gridApi.selection.getSelectedRows();
            if (sel.length > 0) {
              $
                  .ajax({
                    type : 'POST',
                    contentType : "application/json",
                    url : '/mls-tool/getRets?mlsId=' + sel[0].mlsOrgId
                        + '&index=0',
                    dataType : 'text',
                    success : function(data) {
                      alert("success!");
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                      alert("error!");
                    }
                  });
            } else {
              alert("no selected record");
            }
          };
          $scope.toOnline = function() {
            var leftScope = angular.element(leftGridDiv).scope();
            var sel = leftScope.gridApi.selection.getSelectedRows();
            if (sel.length > 0) {
              $
                  .ajax({
                    type : 'POST',
                    contentType : "application/json",
                    url : '/mls-tool/getRets?mlsId=' + sel[0].mlsOrgId
                        + '&index=1',
                    dataType : 'text',
                    success : function(data) {
                      alert("success!");
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                      alert("error!");
                    }
                  });
            } else {
              alert("no selected record");
            }
          };
        } ]);
    app
        .controller(
            'customersCtrl',
            function($scope, i18nService) {
              i18nService.setCurrentLang('zh-cn');
              $scope.myDefs = [
                  {
                    field : 'mlsOrgId',
                    displayName : 'mlsID',
                    cellTemplate : '<a href="{{row.entity.mlsOrgId}}">{{row.entity.mlsOrgId}}</a>',
                    enableSorting : true
                  },
                  {
                    field : 'metaData',
                    displayName : 'metaData'
                  },
                  {
                    field : 'createTime',
                    displayName : 'createTime'
                  },
                  {
                    field : 'updateTime',
                    displayName : 'updateTime'
                  },
                  {
                    field : 'operation',
                    displayName : '',
                    cellTemplate : '<div class="btn-group" role="group"><button type="button" class="btn btn-default" ng-click="loadMeta()">a</button></div>'
                  } ];
              $scope.gridOptions = {
                data : 'dateSource',// 数据
                columnDefs : $scope.myDefs,
                multiSelect : false,
                onRegisterApi : function(gridApi) {
                  $scope.gridApi = gridApi;
                }
              }
            });
  </script>
</body>
</html>