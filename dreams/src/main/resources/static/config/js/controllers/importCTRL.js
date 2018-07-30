conf.controller('importCTRL',['$scope','$http',function ($scope,$http) {
    console.log("entrer dans importCTRL");
    var ElementfileUrl = serverAdresse+"api/uploadElementfile";
    var EnrolDatafileUrl = serverAdresse+"api/uploadEnrolDatafile";
    $scope.chargeData = false;
    $scope.chargeElement = false;


    $scope.doUploadMetaFile = function(){
        console.log("uploadMetaFileController $scope.doUploadFile");
        $scope.chargeElement = true;
        var file = $scope.uploadedFileMeta;

        var data = new FormData();
        data.append('uploadfile', file);

        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': undefined
            }
        };

        $http.post(ElementfileUrl, data, config).then(function (response) {
            $scope.uploadResult=response.data;
            $scope.FileMeta = null;
            $scope.chargeElement = false;
            $scope.uploadedFileMeta = null;
        }, function (error) {
            $scope.uploadResult=error.data;
            $scope.FileMeta = null;
            $scope.chargeElement = false;
            $scope.uploadedFileMeta = null;
        });
    };


    $scope.doUploadDataFile = function(){
        console.log("uploadDataFileController doUploadFile");
        $scope.chargeData = true;
        var file = $scope.uploadedFileData;


        var data = new FormData();
        data.append('uploadfile', file);

        var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers : {
                'Content-Type': undefined
            }
        };

        $http.post(EnrolDatafileUrl, data, config).then(function (response) {
            console.log("doUploadDataFile() > response = ",response);
            $scope.uploadResult=response.data;
            $scope.chargeData = false;
            $scope.FileData = null;
            $scope.uploadedFileData = null;
        }, function (error) {
            console.log("doUploadDataFile() > error = ",error);
            $scope.uploadResult=error.data;
            $scope.chargeData = false;
            $scope.FileData = null;
            $scope.uploadedFileData = null;
        });
    };
    
    
}]);