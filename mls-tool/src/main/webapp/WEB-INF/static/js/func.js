function getResources(meta) {
	var resources = [];
	$.each(meta, function(key, data) {
		resources.push(key);
	});
	return resources;
}

function getIndexes(meta) {
	var indexes = [];
	for (var i = 0; i < meta.length - 1; i++) {
		indexes[i] = i;
	}
	return indexes;
}

function syntaxHighlight(json) {
	if (typeof json != 'string') {
		json = JSON.stringify(json, undefined, 2);
	}
	return json
			.replace(
					/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g,
					function(match) {
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

function clearSampleData() {
	var scope = angular.element(mainDiv).scope();
	angular.forEach(
			mls_meta[scope.selectedResource][scope.selectedClass]["data"],
			function(value, key) {
				value.sample = "";
			});
}

function setSampleData(num) {
	var scope = angular.element(mainDiv).scope();
	angular
			.forEach(
					mls_meta[scope.selectedResource][scope.selectedClass]["dataDisplay"],
					function(value, key) {
						if (sampleData&&sampleData[num]) {
							value.sample = sampleData[num][value.colname];
						} else {
							value.sample = "";
						}

					});
}

function onClazzChanged() {
	var scope = angular.element(mainDiv).scope();
	$.ajax({
		type : 'POST',
		contentType : "application/json",
		url : '/mls-tool/get/sample-data?mlsId=' + mlsId + '&res='
				+ scope.selectedResource + "&clazz=" + scope.selectedClass,
		dataType : 'json',
		success : function(data) {
			sampleData = data;
			var temp = [];
			for ( var key in data) {
				temp.push(key);
			}
			scope.samples = temp;
			scope.selectedSample = scope.samples[0];
			setSampleData(1);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			clearSampleData();
		}
	});
}