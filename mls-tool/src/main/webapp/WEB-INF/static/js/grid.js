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
									displayName : '字段名'
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
						$scope.mlsGrid = {
							data : 'mlsdateSource',
							columnDefs : $scope.myDefs,
							enableFiltering : true,
							onRegisterApi : function(gridApi) {
								$scope.gridApi = gridApi;
							}
						}
					});
	app
			.controller(
					'customersCtrl',
					function($scope) {
						$scope
								.$watch(
										'selectedClass',
										function(newValue, oldValue) {
											$scope.dateSource = lcolJSON[$scope.selectedResource][newValue]["data"];
										});
						$scope.myDefs = [
								{
									field : 'columnName',
									enableCellEdit : false,
									width : '150px',
									displayName : '字段名'
								},
								{
									field : 'columnComment',
									enableCellEdit : false,
									width : '150px',
									displayName : '描述'
								},
								{
									field : 'dataType',
									width : '60px',
									enableCellEdit : false,
									displayName : '字段类型',
									visible : false
								},
								{
									field : 'mlsCols',
									width : '160px',
									enableCellEdit : true,
									displayName : '匹配字段'}, {
									field : 'js',
									enableCellEdit : true,
									width : '320px',
									displayName : '解析函数'
								} ];
						$scope.gridOptions = {
							i18n : 'zh-cn',
							data : 'dateSource',// 数据
							columnDefs : $scope.myDefs,// 表头
							enableCellEditOnFocus : true,
							selectedItems : $scope.leftSeltected,// 选择某行这一行的对象就赋值给$scope.selectedItem
							multiSelect : false,// 不可多选
							showSelectionCheckbox : true,
							showFilter : true,
							showColumnMenu : true,
							afterSelectionChange : function(rowItem, event) {
								var scope = angular.element(mlsGridDiv).scope();
								scope.gridApi.selection.clearSelectedRows();
							}
						}
					});
}