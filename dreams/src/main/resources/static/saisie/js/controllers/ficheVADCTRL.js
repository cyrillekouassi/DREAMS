saisie.controller('ficheVADCTRL',['$scope','$rootScope','$http','$stateParams','$state','$timeout','$filter',function ($scope,$rootScope,$http,$stateParams,$state,$timeout,$filter) {
    console.log("entrer dans ficheVADCTRL");
    var dataValueUrl = serverAdresse+"dataValue/";
    var ValueUrl = serverAdresse+'dataValue';
    var saveValueUrl = serverAdresse+'dataValue/saveValue';
    var searchbeneficiaire = serverAdresse+"beneficiaire?";
    var DATE_FORMAT_ENVOIE = 'yyyy-MM-dd';
    $scope.vadData = {};
    var vadDataOrigine = {};
    $scope.motif = {};
    var dataInstance = {};
    var elementCode = [];
    var instanceValue = [];
    var instanceValueEnrolement = [];
    var dataInstanceEntete = {};
    var autocompleteData = [];
    //var vadMeta = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    console.log("avec Params dataInstanceEntete : ",dataInstanceEntete);
    initMotif();
    initOrg();

    if(dataInstanceEntete.instance){
        if($rootScope.allData && $rootScope.allData.length != 0){
            for(var i=0;i<$rootScope.allData.length;i++){
                if($rootScope.allData[i].instance == dataInstanceEntete.instance){
                    instanceValue = $rootScope.allData[i].dataValue;
                    mappigData();
                }
            }
        }else{
            getdataInstace(dataInstanceEntete.instance,"ok");
        }
    }
    function getdataInstace(instance,orig){
        
        var dataInstanceValue = dataValueUrl+instance;
        $http.get(dataInstanceValue).then(function (response) {
            console.log("getdataInstace() response",response);
            if (response) {
                if(orig=='ok'){
                    instanceValue = response.data.dataValue;
                    mappigData();
                }else
                    instanceValueEnrolement = response.data.dataValue;
                    gestComplete();
            }
        }, function (err) {
            console.log("getdataInstace() err",err);
        });
    }
    function initOrg() {
        $scope.vadData.nomSafespace = $rootScope.orgUnitSelect.name;
    }
    
    function mappigData() {
        
        var temp = {};
        for(var i=0;i<instanceValue.length;i++){
            for(var j=0;j<$rootScope.programmeSelect.elements.length;j++){
                if(instanceValue[i].element == $rootScope.programmeSelect.elements[j].element.id){
                    temp[$rootScope.programmeSelect.elements[j].element.code] = instanceValue[i].value;
                    break;
                }
            }
        }
        $scope.vadData = temp;
        getMotif();
        console.log("mappigData => $scope.vadData : ",$scope.vadData);
        vadDataOrigine = angular.copy($scope.vadData);
        initOrg();
    }
    
    function initMotif() {
        $scope.motif = {};
        $scope.motif.psycho = false;
        $scope.motif.medical = false;
        $scope.motif.juridique = false;
        $scope.motif.depistage = false;
        $scope.motif.education = false;
        $scope.motif.economique = false;
        $scope.motif.preservatif = false;
        $scope.motif.pf = false;
        $scope.motif.alimentaire = false;
        $scope.motif.nutrition = false;
        $scope.motif.autre = false;

    }
    function getMotif() {
        if($scope.vadData.motifVAD){
                var init = 0,indice = 0; var cont = true;
                while(cont){
                    var mot = "";
                    indice = $scope.vadData.motifVAD.indexOf(' ',init);
                    if(indice != -1){
                        mot = $scope.vadData.motifVAD.substring(init, indice);
                    }else{
                        mot = $scope.vadData.motifVAD.substring(init);
                        cont = false;
                    }
                    console.log("init = ",init);
                    console.log("indice = ",indice);
                    console.log("mot = ",mot);
                    console.log("$scope.vadData.motifVAD = ",$scope.vadData.motifVAD);
                    $scope.motif[mot] = true;
                    init = indice + 1;

                }

        }
    }
    

    $scope.saveVAD = function () {
        if(dataInstanceEntete.instance && vadDataOrigine == $scope.vadData){
            console.log("vadMeta.instance existe = "+dataInstanceEntete.instance);
            $state.go('ficheVADList',{org: $rootScope.orgUnitSelect.id});
        }else{
            console.log("dataInstanceEntete = "+angular.copy(dataInstanceEntete));
            console.log("dataInstanceEntete.instance = "+angular.copy(dataInstanceEntete.instance));
            delete dataInstanceEntete.instance;
            checkMotif();
            dataInstance = dataInstanceEntete;
            dataInstance.dreamsId = $scope.vadData.id_dreams;
            dataInstance.dateActivite = $scope.vadData.dateVAD;
            //  dataInstance.dateActivite = formadate($scope.vadData.dateVAD);
            console.log("dataInstance.dateActivite = ",dataInstance.dateActivite);
            dataInstance.order = 1;
            dataInstance.dataValue = [];
            elementCode = Object.keys($scope.vadData);
            console.log("elementCode = ",elementCode);
            getElementid();
        }

    };

    function formadate(date) {
        var jour = date.substring(0,2);
        var mois = date.substring(3,5);
        var anne = date.substring(6,date.length);
        return anne+"-"+mois+"-"+jour;
    }
    
    function checkMotif() {
        console.log("checkMotif() $scope.vadData = ",$scope.vadData);
        for (var prop in $scope.motif) {
            //console.log('checkMotif() prop=', prop);
           // console.log('checkMotif() prop=', $scope.motif[prop]);
            if($scope.motif[prop]){
                if($scope.vadData.motifVAD){
                    $scope.vadData.motifVAD = $scope.vadData.motifVAD+" "+prop
                }else{
                    $scope.vadData.motifVAD = ""+prop
                }
            }
        }
    }
    
    function getElementid() {
        console.log("getElementid() $scope.vadData = ",$scope.vadData);
        var data = {};
        for(var i=0;i<elementCode.length;i++){
            for(var j = 0;j<$rootScope.programmeSelect.elements.length;j++){
                //if($rootScope.element[j].code){
                    if($rootScope.programmeSelect.elements[j].element.code == elementCode[i]){
                        var data = {};
                        data.element = $rootScope.programmeSelect.elements[j].element.id;
                        data.value = $scope.vadData[elementCode[i]];
                        data.numero = 1;
                        dataInstance.dataValue.push(data);
                    }
               // }
            }
        }
        console.log("dataInstance = ",dataInstance);
        saveData();
    }
    
    function saveData() {
        
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(ValueUrl, dataInstance, config).then(function(succes){
            console.log("succes = ",succes);
            succesSave();
        }, function(error){
            console.log("error = ",error);
            echecSave();
        });
    }

    function succesSave() {
        //$state.go('ficheVADList');
        $state.go('ficheVADList',{org: $rootScope.orgUnitSelect.id});
    }
    function echecSave() {
        //$state.go('ficheVADList');
        $state.go('ficheVADList',{org: $rootScope.orgUnitSelect.id});
    }

    $scope.saveValue = function (element,value) {
        /*console.log("element = ",element);
        console.log("value = ",value);
        console.log("instanceValue = ",instanceValue);
        console.log("$rootScope.elementProgramme = ",$rootScope.elementProgramme);
        if(dataInstanceEntete.instance){
            var valueTDO = {};
            var elementId = getElementId(element);

            for(var j=0;j<instanceValue.length;j++){
                if(elementId == instanceValue[j].element){
                    valueTDO = instanceValue[j];
                    break;
                }
            }

            if(!valueTDO.instance){
                valueTDO.element = elementId;
                valueTDO.instance = dataInstanceEntete.instance;
            }

            if(valueTDO.value != value){
                valueTDO.value = value;
                sendValueTDO(valueTDO);
            }
        }*/
        //$timeout(envoieDonnees(element,value), 500);

    };
    

    function sendValueTDO(valueTDO) {
        
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(saveValueUrl, valueTDO, config).then(function(succes){
            console.log("sendValueTDO succes = ",succes);
        }, function(error){
            console.log("sendValueTDO error = ",error);
        });
    }
    
    $scope.searchId = function (element, valeur) {

        if(valeur.length > 2){
            console.log("searchId valeur = ",valeur);
            var org = "organisation="+dataInstanceEntete.organisation;
            var value = "idDreams="+valeur;
            
            var datavalueSearch = searchbeneficiaire+org+"&"+value;
            $http.get(datavalueSearch).then(function (response) {
                console.log("searchId() response",response);
                autocompleteData = response.data;
                chargeData();
            }, function (err) {
                console.log("searchId() err",err);
            });
        }

    };

    function getElementId(code) {
        for(var j=0;j<$rootScope.elementProgramme.length;j++){
            if(code == $rootScope.elementProgramme[j].code){
                return $rootScope.elementProgramme[j].id;
            }
        }
    }
    
    function getProgrammeId(code) {
        for(var j=0;j<$rootScope.programme.length;j++){
            if(code == $rootScope.programme[j].code){
                return $rootScope.programme[j].id;
            }
        }
    }
    
    function chargeData() {
        $scope.valueComplete = [];
        for(var i = 0, j=autocompleteData.length;i<j;i++){
            $scope.valueComplete.push(autocompleteData[i].id_dreams);
        }
        console.log("chargeData() $scope.valueComplete = ", $scope.valueComplete);
    }

    $scope.fillTextbox=function(string){
        $scope.vadData.id_dreams=string;
        $scope.valueComplete=null;
        $scope.saveValueId();
    };

    $scope.saveValueId = function () {
        console.log("saveValueId() $scope.vadData.id_dreams = ",angular.copy($scope.vadData.id_dreams));
        $timeout( function(){
            console.log("saveValueId() $scope.vadData.id_dreams = ",angular.copy($scope.vadData.id_dreams));
            for(var i =0,j=autocompleteData.length;i<j;i++){
                if($scope.vadData.id_dreams == autocompleteData[i].id_dreams){
                    gestComplete(autocompleteData[i]);
                    //gestComplete(BenefInfo)
                }
            }
        }, 10);

    };

    function gestComplete(BenefInfo) {
        $scope.vadData.nomBeneficiaire = BenefInfo.name+" "+BenefInfo.firstName;

    }
    
}]);