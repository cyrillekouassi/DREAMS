saisie.controller('dossierBeneficiaireCTRL', ['$scope', '$rootScope', '$http', '$stateParams', '$state', '$timeout','$filter', function ($scope, $rootScope, $http, $stateParams, $state, $timeout,$filter) {
    console.log("entrer dans dossierBeneficiaireCTRL");
    var dataInstanceValue = serverAdresse + "dataValue/";
    var dataValueUrl = serverAdresse + 'dataValue';
    var searchBeneficiaire = serverAdresse + "beneficiaire?";
    var dataInstance = {};
    var dataInstanceEntete = {};


    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = dataInstanceEntete;
    var instanceValue = [];

    var dossierBenef = {};
    $scope.dossier = {};
    $scope.porteEntree = {};
    $scope.categorieDreams = {};
    var autocompleteData = [];
    var dossierTable = ["nomONG", "region", "sousPrefect", "centre_social", "departement", "villageCommune", "quatierBenef", "repereHabitation", "nomPrenomBenef", "age", "contactTeleph", "nomPrenomParent", "contactParent","dateEnrolement", "id_dreams"];

    var besoinTable = ["porteEntree","categorieDreams","educationSexuel", "actifsSociaux", "soutienPsychoSocial", "renforcementSocioEconomique", "soutienEducatif", "accesPreservatifs", "contraceptionMixte", "conseilsDepistage", "soinsPostVBG", "securiteAlimentaire", "protection", "communicationParentsEnfants", "renforcementEconomique"];
    var servicesPrimaireTable = ["conceptSexualite", "conceptsGenre", "connaissanceCorpsOrgane", "aspectsNegatifs", "promotionDepistage", "participationActivites", "participationCauseries", "ecouteConseils", "suivi", "referenceVersExperts", "businessPlus", "participationAVEC"];
    var servicesSecondaireTable = ["appuiScolaire", "fournitures", "uniformes", "autre", "alphabetisation", "utilisationPreservatifs", "distributionPreservatifs", "referencePreservatifs", "referencVersPF", "referenceServices","referenceMedical","referencePsychoSocial","referenceJuridique","referenceAbri","appuiMedicaux","appuiJuridiques","referenceNutritionnel","appuiDocument"];
    var servicesContextTable = ["sinovoyu","AVEC","educationFinanciere"];

    initial();
    function initial() {
        $('.datepicker').pickadate({
            closeOnSelect: false,
            closeOnClear: false
        });
        $(document).ready(function () {
            $('.mdb-select').material_select();
        });
        $(function () {
            // Tooltip Initialization

            $('.dates input').datepicker({
                format: "dd-mm-yyyy",
                weekStart: 1,
                todayBtn: "linked",
                clearBtn: true,
                language: "fr",
                daysOfWeekHighlighted: "0,6",
                autoclose: true,
                todayHighlight: true
            });
        });
    }

    function initOrg() {
            $scope.dossier.nomONG = $rootScope.orgUnitSelect.organisationLocal;
            $scope.dossier.region = $rootScope.orgUnitSelect.region;
            $scope.dossier.sousPrefect = $rootScope.orgUnitSelect.sousPrefecture;
            $scope.dossier.commune = $rootScope.orgUnitSelect.commune;
            $scope.dossier.departement = $rootScope.orgUnitSelect.departement;
            $scope.dossier.quatierBenef = $rootScope.orgUnitSelect.quartier;

    }

    if (dataInstanceEntete.instance) {
        if ($rootScope.allData) {
            for (var i = 0; i < $rootScope.allData.length; i++) {
                if ($rootScope.allData[i].instance == dataInstanceEntete.instance) {
                    instanceValue = $rootScope.allData[i].dataValue;
                    console.log("instanceValue = ", angular.copy(instanceValue));
                    mappigData();
                }
            }
        }
    }else {
        initOrg();
    }
    function mappigData(){
        console.log("mappigData() $rootScope.programmeSelect = ",$rootScope.programmeSelect);
        getDossierElement();
        getBesoinElement();
        getServicesPrimaireElement();
        getServicesSecondaireTable();
        getServicesContextTable();
    }

    function getElementId(code) {
        for(var i=0;i<$rootScope.programmeSelect.elements.length;i++){
            if($rootScope.programmeSelect.elements[i].element.code == code){
                return $rootScope.programmeSelect.elements[i].element.id;
            }
        }
    }
    function getElementByCode(code) {
        for(var i=0;i<$rootScope.programmeSelect.elements.length;i++){
            if($rootScope.programmeSelect.elements[i].element.code == code){
                return $rootScope.programmeSelect.elements[i].element;
            }
        }
    }

    function getDossierElement(){
        for(var i =0;i<dossierTable.length;i++){
            var id = getElementId(dossierTable[i]);
            for(var j=0;j<instanceValue.length;j++){
                if(id == instanceValue[j].element){
                    $scope.dossier[dossierTable[i]] = instanceValue[j].value;
                    break;
                }
            }
        }
    }

    function getBesoinElement(){
        for(var i =0;i<besoinTable.length;i++){

            var id = getElementId(besoinTable[i]);
            for(var j=0;j<instanceValue.length;j++){
                if(id == instanceValue[j].element){
                    $scope[besoinTable[i]] = {};
                    $scope[besoinTable[i]] = getOptionValue(instanceValue[j].value);
                    break;
                }
            }
        }
    }

    function getOptionValue(data) {
        var lesData = {};
        if(!data){return null}
        var conti = true;
        var space = -2,initi = 0;
        while (conti){
            space = data.indexOf(" ",initi);
            if(space != -1){
                lesData[data.substring(initi, space)] = true;
                initi = space+1;
            }else{
                lesData[data.substring(initi, data.length)] = true;
                conti = false;
            }
        }
        return lesData;
    }

    function getServicesPrimaireElement(){
        for(var i =0;i<servicesPrimaireTable.length;i++){
            var id = getElementId(servicesPrimaireTable[i]);
            for(var j=0;j<instanceValue.length;j++){
                if(id == instanceValue[j].element){
                    $scope[servicesPrimaireTable[i]] = {};
                    $scope[servicesPrimaireTable[i]] = separeSpace(instanceValue[j].value);
                    break;
                }
            }
        }
    }

    function getServicesSecondaireTable(){
        for(var i =0;i<servicesSecondaireTable.length;i++){
            var id = getElementId(servicesSecondaireTable[i]);
            for(var j=0;j<instanceValue.length;j++){
                if(id == instanceValue[j].element){
                    $scope[servicesSecondaireTable[i]] = {};
                    $scope[servicesSecondaireTable[i]] = separeSpace(instanceValue[j].value);
                    break;
                }
            }
        }
    }

    function getServicesContextTable(){
        for(var i =0;i<servicesContextTable.length;i++){
            var id = getElementId(servicesContextTable[i]);
            for(var j=0;j<instanceValue.length;j++){
                if(id == instanceValue[j].element){
                    $scope[servicesContextTable[i]] = {};
                    $scope[servicesContextTable[i]] = separeSpace(instanceValue[j].value);
                    break;
                }
            }
        }
    }

    function separeSpace(data) {
        console.log("separeSpace() data = ",data);
        var lesData = [];
        var tmp = {};
        if(!data){return null;}
        var conti = true;
        var space = -2,initi = 0;
        while (conti){
            space = data.indexOf(" ",initi);
            if(space != -1){
                lesData.push(data.substring(initi, space));
                initi = space+1;
            }else{
                lesData.push(data.substring(initi, data.length));
                conti = false;
            }
        }
        console.log("separeSpace() lesData = ",lesData);
        for(var i =0;i<lesData.length;i= i+2){
            tmp[lesData[i]] = lesData[i+1];
        }
        console.log("separeSpace() tmp = ",tmp);
        return tmp;
    }

    $scope.saveDossier = function () {
        console.log("saveDossier");
        if(!dataInstance.instance){
            delete dataInstance.instance;
        }
        console.log("dataInstance = ",dataInstance);
        dossierBenef = angular.copy($scope.dossier);
        gestBesoinTable();
        gestServicesPrimaireTable();
        gestServicesSecondaireTable();
        gestServicesContextTable();
        console.log("dossierBenef = ",dossierBenef);
        console.log("getElement() $rootScope.programmeSelect = ",$rootScope.programmeSelect);
        dataInstance.dataValue = [];
        dataInstance.dreamsId = $scope.dossier.id_dreams;
        //dataInstance.dateActivite = $filter('date')(new Date(), 'dd-MM-yyyy');
        dataInstance.dateActivite = $scope.dossier.dateEnrolement;
            dataInstance.order = 1;
        for(var pop in dossierBenef){
            if(dossierBenef[pop] != null && dossierBenef[pop] != ""){
                var tpm = getElement(pop, dossierBenef[pop]);
                if(angular.isObject(tpm)){
                    dataInstance.dataValue.push(tpm);
                }
            }
        }
        console.log("dataInstance = ",dataInstance);
        saveData();
    };

    function gestBesoinTable(){
        console.log("gestBesoinTable()  =====> ");
        for(var i =0;i<besoinTable.length;i++){
            if($scope[besoinTable[i]]){
                dossierBenef[besoinTable[i]] = gestOption($scope[besoinTable[i]]);
            }
        }
    }

    function gestOption(object){
        var value = null;
        for(var pop in object){
            if(object[pop]){
                if(!value){
                    value = pop;
                }else{
                    value = value+" "+pop;
                }
            }
        }
        return value;
    }

    function gestServicesPrimaireTable(){
        console.log("gestServicesPrimaireTable()  =====> ");
        for(var i =0;i<servicesPrimaireTable.length;i++){
            if($scope[servicesPrimaireTable[i]]){
                //console.log("gestServicesPrimaireTable = ",servicesPrimaireTable[i]," // value = ",$scope[servicesPrimaireTable[i]]);
                dossierBenef[servicesPrimaireTable[i]] = gestValue($scope[servicesPrimaireTable[i]]);
            }
        }
    }

    function gestServicesSecondaireTable(){
        console.log("gestServicesSecondaireTable()  =====> ");
        for(var i =0;i<servicesSecondaireTable.length;i++){
            if($scope[servicesSecondaireTable[i]]){
                //console.log("gestServicesPrimaireTable = ",servicesSecondaireTable[i]," // value = ",$scope[servicesSecondaireTable[i]]);
                dossierBenef[servicesSecondaireTable[i]] = gestValue($scope[servicesSecondaireTable[i]]);
            }
        }
    }

    function gestServicesContextTable(){
        console.log("gestServicesContextTable()  =====> ");
        for(var i =0;i<servicesContextTable.length;i++){
            if($scope[servicesContextTable[i]]){
                //console.log("gestServicesPrimaireTable = ",servicesContextTable[i]," // value = ",$scope[servicesContextTable[i]]);
                dossierBenef[servicesContextTable[i]] = gestValue($scope[servicesContextTable[i]]);
            }
        }
    }

    function gestValue(object){
        //console.log("gestValue() object = ",object);
        var value = null;
        for(var pop in object){
            //console.log("pop =",pop," value = ",object[pop]);
            if(object[pop] != null && object[pop] != ""){
                if(!value){
                    value = pop+" "+object[pop];
                }else{
                    value = value+" "+pop+" "+object[pop];
                }
            }
        }
        return value;
    }

    function getElement(code, value){
        //console.log("getElement() code = ",code," // value = ",value);
        var data = {};
        for(var i=0;i<$rootScope.programmeSelect.elements.length;i++){
            if($rootScope.programmeSelect.elements[i].element.code == code){
                data.element = $rootScope.programmeSelect.elements[i].element.id;
                data.numero = 1;
                data.value = value;
                return data;
            }
        }
        console.error("getElement() code = ",code," // value = ",value);
    }

    function saveData() {

        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(dataValueUrl, dataInstance, config).then(function(succes){
            console.log("succes = ",succes);
            succesSave();
        }, function(error){
            console.log("error = ",error);
            echecSave();
        });
    }

    $scope.searchId = function (valeur) {

        if(valeur.length > 2){
            console.log("searchId valeur = ",valeur);
            var org = "organisation="+dataInstanceEntete.organisation;
            var value = "idDreams="+valeur;

            var datavalueSearch = searchBeneficiaire+org+"&"+value;
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

    $scope.codeSelected=function(BenefId){
        $scope.dossier.id_dreams=BenefId;
        $scope.valueComplete=null;
        gestBenef(BenefId);
    };

    function gestBenef(BenefId) {
        for(var i =0,j=autocompleteData.length;i<j;i++){
            if(BenefId == autocompleteData[i].id_dreams){
                console.log("gestBenef() autocompleteData = ", autocompleteData[i]);
               $scope.dossier.nomPrenomBenef=autocompleteData[i].name+" "+autocompleteData[i].firstName;
                $scope.dossier.age=autocompleteData[i].ageEnrolement;
                $scope.dossier.contactTeleph=autocompleteData[i].telephone;
                $scope.dossier.dateEnrolement= $filter('date')(new Date(autocompleteData[i].dateEnrolement), 'dd-MM-yyyy');
                //$scope.dossier.id_dreams=string;$filter('date')(new Date(autocompleteData[i].dateEnrolement), 'dd-MM-yyyy');
            }
        }
    }

    function succesSave() {
        sortir();
    }
    function echecSave() {
        sortir();
    }
    function sortir() {
        $state.go('dossierList',{org: $rootScope.orgUnitSelect.id});
    }

}]);