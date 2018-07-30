analyse.controller('rapportCTRL', ['$scope', '$rootScope', '$stateParams', '$http', function ($scope, $rootScope, $stateParams, $http) {
    console.log("entrer dans rapportCTRL");
    console.log("$rootScope.collectIndicateursSeclect = ",$rootScope.collectIndicateursSeclect);
    console.log("$rootScope.periodeSelect = ",$rootScope.periodeSelect);
    console.log("$rootScope.orgUnitSelect = ",$rootScope.orgUnitSelect);
    var rapportValueUrl = serverAdresse+"rapport";
    var periode = null;
    var element = null;
    var lesData = [];
    $scope.periode = angular.copy($rootScope.periodeSelect);
    $scope.organisation = angular.copy($rootScope.orgUnitSelect);
    $scope.indicateurValue = angular.copy($rootScope.collectIndicateursSeclect);
    console.log("$scope.periode = ",$scope.periode);
    console.log("$scope.organisation = ",$scope.organisation);
    console.log("$scope.indicateurValue = ",$scope.indicateurValue);

    formatData();

    function formatData(){
        for(var i = 0;i<$rootScope.collectIndicateursSeclect.length;i++){
            if(!element){
                element = $rootScope.collectIndicateursSeclect[i].id;
            }else{
                element = element+","+$rootScope.collectIndicateursSeclect[i].id;
            }

        }
        for(var i = 0;i<$rootScope.periodeSelect.length;i++){
            //periode += $rootScope.periodeSelect[i].id;
            if(!periode){
                periode = $rootScope.periodeSelect[i].id;
            }else{
                periode = periode+","+$rootScope.periodeSelect[i].id;
            }
        }
        collectData();
    }

    function collectData() {
        var org = "organisation="+$rootScope.orgUnitSelect.id;
        var periodeData = "periode="+periode;
        var elementData = "element="+element;
        var dataValueUrl = rapportValueUrl+"?"+org+"&"+elementData+"&"+periodeData;
        console.log("dataValueUrl = "+dataValueUrl);
        $http.get(dataValueUrl).then(function (response) {
            console.log("collectData(), response = ",response);
            lesData = response.data;
            if(lesData.length > 0){
                traitementData();
            }
        }, function (err) {
            console.log("err",err);
        });
    }

    function traitementData() {
        for(var i = 0; i < $scope.indicateurValue.length;i++){
            $scope.indicateurValue[i].lesPeriode = {};
            for(var j=0;j<lesData.length;j++){
                if($scope.indicateurValue[i].id == lesData[j].element){
                    $scope.indicateurValue[i].lesPeriode[lesData[j].periode] = lesData[j].valeurs;
                }
            }
        }
        console.log("$scope.indicateurValue = ",$scope.indicateurValue);
    }
}]);