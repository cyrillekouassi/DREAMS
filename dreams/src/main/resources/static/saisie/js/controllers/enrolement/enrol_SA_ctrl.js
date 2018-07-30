saisie.controller('enrol_SA_Ctrl', ['$scope', '$rootScope', '$stateParams', '$http','$filter','$state', function ($scope, $rootScope, $stateParams, $http,$filter,$state) {
    console.log("entrer dans enrol_SA_Ctrl");
    var ValueUrl = serverAdresse+'dataValue';
    var valueBeneficiaire = serverAdresse+'beneficiaire';
    var dataInstanceEntete = {};
    var dataInstance = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = angular.copy(dataInstanceEntete);
    $scope.enrolA = {};
    $rootScope.enrolementData = [];
    var centreSocial = getInfoOrganisation($rootScope.orgUnitSelect.parent.id);
    var plateForme = getInfoOrganisation(centreSocial.parent.id);
    var beneficiaire = {};
    var enrolSectionA = ["district","plateform_cod","centre_social","ong","codeSafespace","nom_enqueteur","num_fiche",
        "nom","pren","dat_nais","dat_enrol","age_enrol","porte_entre_bene","autre_porte_entre_bene","statut_mat_bene",
        "ocup","oc_aut","region","departement","sousPrefect","villageCommune","repere","no_benef","id_dreams",
        "nom_charge_bene","relation","tel_charg_benef","_01_b_num_doc","_01_doc_att_age","_01_a_type_doc",
        "_02_accord_parent","_03_acord_bene"];

    initial();
    initialDom();
    function initial() {
        console.log("entrer dans initial, $rootScope.orgUnitSelect", $rootScope.orgUnitSelect);

        //$scope.enrolA.district = "";
        $scope.enrolA.plateform_cod = plateForme.name;
        $scope.enrolA.centre_social = centreSocial.name;
        $scope.enrolA.ong = $rootScope.orgUnitSelect.organisationLocal;
        $scope.enrolA.codeSafespace = $rootScope.orgUnitSelect.code;
        $scope.enrolA.region = $rootScope.orgUnitSelect.region;
        $scope.enrolA.departement = $rootScope.orgUnitSelect.departement;
        $scope.enrolA.sousPrefect = $rootScope.orgUnitSelect.sousPrefecture;
        $scope.enrolA.villageCommune = $rootScope.orgUnitSelect.commune;

    }
    if(dataInstance.instance){
        if($rootScope.allData && $rootScope.allData.length != 0){
            for(var i=0;i<$rootScope.allData.length;i++){
                if($rootScope.allData[i].instance == dataInstanceEntete.instance){
                    $rootScope.enrolementData = $rootScope.allData[i].dataValue;
                    mappigData();
                }
            }
        }
    }

    function mappigData() {
        for(var i = 0;i<enrolSectionA.length;i++){
            var id = getElementId(enrolSectionA[i]);
            if(id){
                for(var j=0;j<$rootScope.enrolementData.length;j++){
                    if(id == $rootScope.enrolementData[j].element){
                        $scope.enrolA[enrolSectionA[i]] = $rootScope.enrolementData[j].value;
                    }
                }
            }
        }
        console.log("mappigData() $scope.enrolA = ",$scope.enrolA);
    }

    function initialDom() {
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

    function getInfoOrganisation(id) {
        console.log("entrer dans getInfoOrganisation, id = ",id," // $rootScope.organisation", $rootScope.organisation);
        for(var i = 0;i<$rootScope.organisation.length;i++){
            if(id == $rootScope.organisation[i].id){
                return $rootScope.organisation[i];
            }
        }
    }

    $scope.getBenefId = function() {
        console.log("entrer dans getBenefId()");
        var mois = $scope.enrolA.dat_enrol.substring(3,5);
        var annee = $scope.enrolA.dat_enrol.substring(8,10);
        $scope.enrolA.id_dreams = "D/"+plateForme.code+"/"+centreSocial.code+"/"+annee+"/"+mois+"/"+$scope.enrolA.codeSafespace+"/"+$scope.enrolA.no_benef;
    };

    $scope.savePage = function () {
        console.log("entrer dans savePage()");
        console.log("entrer dans savePage(),$scope.enrolA = ",$scope.enrolA);
        console.log("entrer dans savePage(),dataInstance = ",dataInstance);
        dataInstance.dataValue = [];
        dataInstance.dreamsId = $scope.enrolA.id_dreams;
        dataInstance.dateActivite = $scope.enrolA.dat_enrol;
        //dataInstance.order = 1;
        if(!dataInstance.instance){
            delete dataInstance.instance;
            getElementid();
            createBeneficiaire();
        }else{
            getElementid();
            saveData();
        }
    };

    function getElementid() {
        console.log("getElementid()");
        for(var pop in $scope.enrolA){
            if($scope.enrolA[pop] != null && $scope.enrolA[pop] && $scope.enrolA[pop] != ""){
                var id = getElementId(pop);
                if(id){
                    var data = {};
                    data.element = id;
                    data.value = $scope.enrolA[pop];
                    data.numero = 1;
                    dataInstance.dataValue.push(data);
                }else{
                    console.error("getElementid(). Element non trouvé, code = ",pop)
                }
            }else {
                console.info("getElementid(). Element sans valeur, code = ",pop," // valeur = ",$scope.enrolA[pop]);
            }
        }
        console.log("dataInstance = ",dataInstance);

    }

    function getElementId(code) {
        for(var j = 0;j<$rootScope.programmeSelect.elements.length;j++){
            if($rootScope.programmeSelect.elements[j].element.code == code){
                return $rootScope.programmeSelect.elements[j].element.id;
            }
        }
        return null;
    }

    function saveData() {

        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(ValueUrl, dataInstance, config).then(function(succes){
            console.log("saveData() succes = ",succes);
            //dataInstance.instance =
            if(succes.data.status == "ok"){
                dataInstance.instance = succes.data.id;
                succesSave();
            }else{
                toastr["success"]("Echec d'enregistrement");
            }
        }, function(error){
            console.log("saveData() error = ",error);
            toastr["success"]("Echec d'enregistrement");
        });
    }
    
    function succesSave() {
        $state.go('enrol_SB',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
    }
    
    function createBeneficiaire() {
        beneficiaire = {};
        beneficiaire.name = $scope.enrolA.nom;
        beneficiaire.firstName = $scope.enrolA.pren;
        beneficiaire.id_dreams = $scope.enrolA.id_dreams;
        beneficiaire.telephone = $scope.enrolA.tel;
        beneficiaire.dateNaissance = $scope.enrolA.dat_nais;
        beneficiaire.ageEnrolement = $scope.enrolA.age_enrol;
        beneficiaire.dateEnrolement = $scope.enrolA.dat_enrol;
        beneficiaire.organisation = {};
        beneficiaire.organisation.id = dataInstance.organisation;
        saveBenef();
    }

    function saveBenef() {

        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(valueBeneficiaire, beneficiaire, config).then(function(succes){
            console.log("saveBenef() > succes = ",succes);
            if(succes.data.status == "OK"){
                saveData();
            }else{
                toastr["success"]("Impossible de créer ce bénéficiaire");
            }
        }, function(error){
            console.log("saveBenef() > error = ",error);
            toastr["success"]("Impossible de créer ce bénéficiaire");
        });
    }

}]);