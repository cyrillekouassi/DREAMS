saisie.controller('ficheReferenceCTRL',['$scope','$stateParams','$rootScope','$http','$state',function ($scope,$stateParams,$rootScope,$http,$state) {
    console.log("entrer dans ficheReferenceCTRL");
    var dataInstanceValue = serverAdresse+"dataValue/";
    var dataValueUrl = serverAdresse+'dataValue';
    var saveValueurl = serverAdresse+'dataValue/saveValue';
    var searchbeneficiaire = serverAdresse+"beneficiaire?";
    $scope.refData = {};
    $scope.motif = {};
    var refDataOrigine = {};
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

    dataInstance = dataInstanceEntete;
    var orig = "enr";
    console.log("avec Params dataInstanceEntete : ",dataInstanceEntete);
    initMotReference();
    initServiceOff();
    initOrg();

    if(dataInstanceEntete.instance){
        if($rootScope.allData){
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

    function initOrg() {
        $scope.refData.nomOrganisation = $rootScope.orgUnitSelect.organisationLocal;
        $scope.refData.commune = $rootScope.orgUnitSelect.commune;
        $scope.refData.quartier = $rootScope.orgUnitSelect.quartier;
    }
    function getdataInstace(instance,orig){
        
        dataInstanceValue += instance;
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

    function mappigData() {
        //getElementOfProgramme();
        var temp = {};
        for(var i=0;i<instanceValue.length;i++){
            for(var j=0;j<$rootScope.programmeSelect.elements.length;j++){
                if(instanceValue[i].element == $rootScope.programmeSelect.elements[j].element.id){
                    temp[$rootScope.programmeSelect.elements[j].element.code] = instanceValue[i].value;
                    break;
                }
            }
        }
        //console.log("mappigData => temp : ",temp);
        $scope.refData = temp;
        getMotif();
        getService();
        console.log("mappigData => $scope.refData : ",$scope.refData);
        refDataOrigine = angular.copy($scope.refData);
        initOrg();
    }

    function initMotReference() {
        $scope.motReference = {};
        $scope.motReference.psycho = false;
        $scope.motReference.medical = false;
        $scope.motReference.juridique = false;
        $scope.motReference.depistage = false;
        $scope.motReference.education = false;
        $scope.motReference.economique = false;
        $scope.motReference.preservatif = false;
        $scope.motReference.pf = false;
        $scope.motReference.alimentaire = false;
        $scope.motReference.nutrition = false;
        $scope.motReference.autre = false;

    }
    function initServiceOff() {
        $scope.service = {};
        $scope.service.psycho = false;
        $scope.service.medical = false;
        $scope.service.juridique = false;
        $scope.service.depistage = false;
        $scope.service.education = false;
        $scope.service.economique = false;
        $scope.service.preservatif = false;
        $scope.service.pf = false;
        $scope.service.alimentaire = false;
        $scope.service.nutrition = false;
        $scope.service.autre = false;

    }
    function getMotif() {
        if($scope.refData.motifref){
            var init = 0,indice = 0; var cont = true;
            while(cont){
                var mot = "";
                indice = $scope.refData.motifref.indexOf(' ',init);
                if(indice != -1){
                    mot = $scope.refData.motifref.substring(init, indice);
                }else{
                    mot = $scope.refData.motifref.substring(init);
                    cont = false;
                }
                console.log("init = ",init);
                console.log("indice = ",indice);
                console.log("mot = ",mot);
                console.log("$scope.refData.motifref = ",$scope.refData.motifref);
                $scope.motReference[mot] = true;
                init = indice + 1;

            }

        }
    }
    function getService() {
        if($scope.refData.serviceOff){
            var init = 0,indice = 0; var cont = true;
            while(cont){
                var mot = "";
                indice = $scope.refData.serviceOff.indexOf(' ',init);
                if(indice != -1){
                    mot = $scope.refData.serviceOff.substring(init, indice);
                }else{
                    mot = $scope.refData.serviceOff.substring(init);
                    cont = false;
                }
                console.log("init = ",init);
                console.log("indice = ",indice);
                console.log("mot = ",mot);
                console.log("getService() $scope.refData.serviceOff = ",$scope.refData.serviceOff);
                $scope.service[mot] = true;
                init = indice + 1;

            }

        }
    }


    $scope.saveREF = function () {
        console.log("dataInstanceEntete = "+angular.copy(dataInstanceEntete));
        console.log("dataInstanceEntete.instance = "+angular.copy(dataInstanceEntete.instance));
        console.log("$scope.refData = "+angular.copy($scope.refData));
        if(refDataOrigine == $scope.refData){
            $state.go('ficheReferenceList',{org: $rootScope.orgUnitSelect.id});
           return;
        }
        if(!dataInstanceEntete.instance){
            delete dataInstanceEntete.instance;
        }
        save();
    };

    function save() {
        checkMotifRef();
        checkServiceOff();
        dataInstance = dataInstanceEntete;
        dataInstance.dreamsId = $scope.refData.id_dreams;
        dataInstance.dateActivite = $scope.refData.dateRef;
        dataInstance.order = 1;
        dataInstance.dataValue = [];
        elementCode = Object.keys($scope.refData);
        console.log("elementCode = ",elementCode);
        getElementid();
    }

    function checkMotifRef() {
        console.log("checkMotifRef() $scope.refData = ",$scope.refData);
        $scope.refData.motifref = null;
        for (var prop in $scope.motReference) {
           // console.log('checkMotifRef() prop=', prop);
           // console.log('checkMotifRef() prop=', $scope.motReference[prop]);
            if($scope.motReference[prop]){
                if($scope.refData.motifref){
                    $scope.refData.motifref = $scope.refData.motifref+" "+prop
                }else{
                    $scope.refData.motifref = ""+prop
                }
            }
        }
    }
    function checkServiceOff() {
        console.log("checkServiceOff() $scope.serviceOff = ",$scope.serviceOff);
        $scope.refData.serviceOff = null;
        for (var prop in $scope.service) {
           // console.log('checkServiceOff() prop=', prop);
            //console.log('checkServiceOff() prop=', $scope.service[prop]);
            if($scope.service[prop]){
                if($scope.refData.serviceOff){
                    $scope.refData.serviceOff = $scope.refData.serviceOff+" "+prop
                }else{
                    $scope.refData.serviceOff = ""+prop
                }
            }
        }
    }

    function getElementid() {
        for(var i=0;i<elementCode.length;i++){
            //console.log(""+elementCode[i]+" = "+$scope.refData[elementCode[i]]);
            for(var j = 0;j<$rootScope.programmeSelect.elements.length;j++){

                    if($rootScope.programmeSelect.elements[j].element.code == elementCode[i]){
                        var data = {};
                        data.numero = 1;
                        data.element = $rootScope.programmeSelect.elements[j].element.id;
                        data.value = $scope.refData[elementCode[i]];
                        dataInstance.dataValue.push(data);
                    }

            }
        }
        console.log("getElementid() dataInstance = ",dataInstance);
        saveData();
    }

    function saveData() {
        
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(dataValueUrl, dataInstance, config).then(function(succes){
            console.log("succes = ",succes);
            Sortie();
        }, function(error){
            console.log("error = ",error);
            Sortie();
        });
    }

    function Sortie() {
        if(orig != "id"){
            $state.go('ficheReferenceList',{org: $rootScope.orgUnitSelect.id});
            //$state.go('ficheReferenceList');
        }
    }

    $scope.saveValue = function (element,value) {
       // console.log("element = ",element);
        /*console.log("value = ",value);
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
                valueTDO.numero = 1;
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

        $http.post(saveValueurl, valueTDO, config).then(function(succes){
            console.log("sendValueTDO succes = ",succes);
        }, function(error){
            console.log("sendValueTDO error = ",error);
        });
    }


    function getElementId(code) {
        for(var j=0;j<$rootScope.elementProgramme.length;j++){
            if(code == $rootScope.elementProgramme[j].code){
                return $rootScope.elementProgramme[j].id;
            }
        }
    }


/*
    
    function chargeData() {
        $scope.valueComplete = [];
        for(var i = 0, j=autocompleteData.length;i<j;i++){
            $scope.valueComplete.push(autocompleteData[i].value);
        }
        //console.log("chargeData() $scope.valueComplete = ", $scope.valueComplete);
    }

    $scope.choisir=function(string){
        $scope.refData.id_dreams=string;
        $scope.valueComplete=null;
        saveValueId(string);
    };

    function saveValueId(string) {
        origin = "idbenef";
        for(var i =0,j=autocompleteData.length;i<j;i++){
            if(string == autocompleteData[i].value){
                getdataInstace(autocompleteData[i].instance)
            }
        }
    }
*/
    $scope.searchId = function (valeur) {
       
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
    function chargeData() {
        $scope.valueComplete = [];
        for(var i = 0, j=autocompleteData.length;i<j;i++){
            $scope.valueComplete.push(autocompleteData[i].id_dreams);
        }
        console.log("chargeData() $scope.valueComplete = ", $scope.valueComplete);
    }

    $scope.choisir=function(string){
        $scope.refData.id_dreams = string;
        $scope.valueComplete=null;
        for(var i =0,j=autocompleteData.length;i<j;i++){
            if($scope.refData.id_dreams == autocompleteData[i].id_dreams){
                gestComplete(autocompleteData[i]);
                break;
            }
        }
    };

    function gestComplete(BenefInfo) {
        console.log("gestComplete() BenefInfo = ", BenefInfo);
        $scope.refData.nom =BenefInfo.name;
        $scope.refData.pren = BenefInfo.firstName;
        $scope.refData.agebenef = BenefInfo.ageEnrolement;
    }

}]);