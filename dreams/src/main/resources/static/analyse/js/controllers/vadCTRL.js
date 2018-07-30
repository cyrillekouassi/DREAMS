analyse.controller('vadCTRL',['$scope','$rootScope','$http',function ($scope,$rootScope,$http) {
    console.log("entrer dans vadCTRL");
    $rootScope.afficheArbre = false;
    var leProgramme = {};
    var leOrganisation = {};
    var elementProgramme = [];
    var lesData = [];
    var lesEnrol = [];

    $scope.listEnrol=[];
    $scope.enrolEntete=[];
    $rootScope.afficheArbre = false;
    $scope.chargement = true;
    $scope.noData = false;

    initial();

    function initial() {
        for(var i=0;i<$rootScope.lesProgrammes.length;i++){
            if($rootScope.lesProgrammes[i].code == "vad"){
                leProgramme = $rootScope.lesProgrammes[i];
                elementProgramme = $rootScope.lesProgrammes[i].elements;
                $scope.enrolEntete = elementProgramme;
                break;
            }
        }
        collecteData();
    }

    function collecteData() {
        var org = "org="+$rootScope.listOrg;
        var prog = "prog="+leProgramme.id;
        var debut = "debut="+$rootScope.periode.debut;
        var fin = "fin="+$rootScope.periode.fin;
        var dataValueUrl = $rootScope.dataValueUrl+"?"+prog+"&"+org+"&"+debut+"&"+fin;
        console.log("dataValueUrl = "+dataValueUrl);
        $http.get(dataValueUrl).then(function (response) {
            console.log(response);
            lesData = response.data;
            if(lesData.length > 0){
                traitementData();
            }else{
                $scope.chargement = false;
                $scope.noData = true;
            }
        }, function (err) {
            console.log("err",err);
        });
    }

    function traitementData() {
        console.log("entrer dans traitementData");
        console.log("elementProgramme = ",elementProgramme);
        for (var k = 0; k < lesData.length; k++) {
            var temp = {};
            if (lesData[k].dataValue) {
                for (var j = 0; j < lesData[k].dataValue.length; j++) {

                    for (var i = 0; i < elementProgramme.length; i++) {
                        if (elementProgramme[i].element.id == lesData[k].dataValue[j].element) {
                            temp[elementProgramme[i].element.code] = [];
                            if (elementProgramme[i].element.ensembleOption && elementProgramme[i].element.ensembleOption.id) {
                                temp[elementProgramme[i].element.code] = getOptionValue(elementProgramme[i].element.ensembleOption.options, lesData[k].dataValue[j].value);
                            } else {
                                temp[elementProgramme[i].element.code].push(lesData[k].dataValue[j].value)
                            }
                            break;
                        }
                    }
                }
            }
            lesEnrol.push(temp);
        }
        console.log("lesEnrol = ",lesEnrol);
        $scope.listEnrol = lesEnrol;
        $scope.chargement = false;
    }

    function getOptionValue(option,data) {
        console.log("entrer dans getOptionValue");
        var conti = true; var debut = 0; var trouve = 0; var lesOption = [];
        while(conti){
            trouve = data.indexOf(" ",debut); var valeur = null;
            if(trouve != -1){
                valeur = constitOption(option,data.substring(debut, trouve));

                debut = trouve+1;
            }else{
                valeur = constitOption(option,data.substring(debut, data.length));
                conti = false;
            }
            lesOption.push(valeur);
        }
        return lesOption;
    }

    function constitOption(options,data) {
        console.log("entrer dans constitOption,options = ",options," // data = ",data);
        for(var k=0;k<options.length;k++){

            if(options[k].code == data){
                return options[k].name;
            }

        }
        return data;
    }

}]);