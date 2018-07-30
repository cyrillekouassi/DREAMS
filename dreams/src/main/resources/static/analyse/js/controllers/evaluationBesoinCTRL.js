analyse.controller('evaluationBesoinCTRL',['$scope','$http','$rootScope',function ($scope,$http,$rootScope) {
    console.log("entrer dans evaluationBesoinCTRL");
    $scope.rouge = "#ff4444";
    $scope.jaune = '#00C851';
    $scope.vert = '#ffbb33';
    var leProgramme = {};
    var leOrganisation = {};
    var elementProgramme = [];
    var lesData = [];
    var lesBesoin = [];
    var lesOrganisation = [];
    var listOrg = [];

    $scope.listBesoins=[];
    $rootScope.afficheArbre = false;
    leOrganisation = $rootScope.orgUnitSelect;
    //$rootScope.pageActive = "besoinBeneficiare";
    initial();
    
    function initial() {
        for(var i=0;i<$rootScope.lesProgrammes.length;i++){
            if($rootScope.lesProgrammes[i].code == "besoinBeneficiare"){
                leProgramme = $rootScope.lesProgrammes[i];
                elementProgramme = $rootScope.lesProgrammes[i].elements;
                break;
            }
        }
        collecteData();
    }

    function collecteData() {
        console.log("$rootScope.periode = ",$rootScope.periode);
        var org = "org="+$rootScope.listOrg;
        var prog = "prog="+leProgramme.id;
        var debut = "debut="+$rootScope.periode.debut;
        var fin = "fin="+$rootScope.periode.fin;
        var dataValueUrl = $rootScope.dataValueUrl+"?"+prog+"&"+org+"&"+debut+"&"+fin;

        console.log("dataValueUrl = ",dataValueUrl);
        $http.get(dataValueUrl).then(function (response) {
            console.log(response);
            if(response){
                console.log("response",response);
                lesData = response.data;
                traitementData();
            }
        }, function (err) {
            console.log("err",err);
        });
    }
    
    function traitementData() {
        console.log("elementProgramme = ",elementProgramme);
        for(var k =0;k<lesData.length;k++){
            var temp={};
            for(var j=0;j<lesData[k].dataValue.length;j++){
                for(var i=0;i<elementProgramme.length;i++){
                    if(elementProgramme[i].element.id == lesData[k].dataValue[j].element){
                        temp[elementProgramme[i].element.code] = {};
                        temp[elementProgramme[i].element.code].score = lesData[k].dataValue[j].numero;
                        temp[elementProgramme[i].element.code].color = lesData[k].dataValue[j].value;
                        break;
                    }
                }
            }
            lesBesoin.push(temp);
        }
        console.log("lesBesoin = ",lesBesoin);
        $scope.listBesoins = lesBesoin;
    }

}]);