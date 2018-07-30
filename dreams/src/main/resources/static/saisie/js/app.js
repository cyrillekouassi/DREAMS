var serverAdresse = "";
var saisie = angular.module('saisie', ['ui.router','tree_directive']);
saisie.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider) {
    var accueil = {
        name: 'accueil',
        url: '/:user',
        templateUrl: 'views/accueil.html',
        controller: 'accueilCTRL'
    };
    var dossier = {
        name: 'dossier',
        url: '/dossierBeneficiaire?prog&org&inst',
        templateUrl: 'views/dossier_beneficiaire.html',
        controller: 'dossierBeneficiaireCTRL'
    };
    var dossierList = {
        name: 'dossierList',
        url: '/dossierBeneficiaireList?org',
        templateUrl: 'views/dossier_beneficiaire_List.html',
        controller: 'dossierBeneficiaireListCTRL'
    };
    var groupe = {
        name: 'activiteGroupe',
        url: '/activiteGroupe?prog&org&inst',
        templateUrl: 'views/activite_groupe.html',
        controller: 'activiteGroupeCTRL'
    };
    var groupeList = {
        name: 'activiteGroupeList',
        url: '/activiteGroupelist?org',
        templateUrl: 'views/activite_groupe_List.html',
        controller: 'activiteGroupeListCTRL'
    };
    var reference = {
        name: 'ficheReference',
        url: '/ficheReference?prog&org&inst',
        templateUrl: 'views/fiche_reference.html',
        controller: 'ficheReferenceCTRL'
    };
    var referenceList = {
        name: 'ficheReferenceList',
        url: '/ficheReferenceList?org',
        templateUrl: 'views/fiche_reference_List.html',
        controller: 'ficheReferenceListCTRL'
    };
    var vad = {
        name: 'ficheVAD',
        url: '/ficheVAD?prog&org&inst',
        templateUrl: 'views/fiche_VAD.html',
        controller: 'ficheVADCTRL'
    };
    var vadList = {
        name: 'ficheVADList',
        url: '/ficheVADList?org',
        templateUrl: 'views/fiche_VAD_List.html',
        controller: 'ficheVadListCTRL'
    };

    var Enrol_List= {
        name: 'enrol_List',
        url: '/enrol_List?org',
        templateUrl: 'views/enrolement/enrol_List.html',
        controller: 'enrol_List_Ctrl'
    };

    var Enrol_SA = {
        name: 'enrol_SA',
        url: '/enrol_SA?prog&org&inst',
        templateUrl: 'views/enrolement/section_A.html',
        controller: 'enrol_SA_Ctrl'
    };
    var Enrol_SB = {
        name: 'enrol_SB',
        url: '/enrol_SB?prog&org&inst',
        templateUrl: 'views/enrolement/section_B.html'
        //controller: 'enrol_SB_Ctrl'
    };
    var Enrol_SC = {
        name: 'enrol_SC',
        url: '/enrol_SC',
        templateUrl: 'views/analyse/section_C.html'
        //controller: 'ficheVadListCTRL'
    };
    var Enrol_SD = {
        name: 'enrol_SD',
        url: '/enrol_SD',
        templateUrl: 'views/analyse/section_D.html'
        //controller: 'ficheVadListCTRL'
    };
    var Enrol_SE = {
        name: 'enrol_SE',
        url: '/enrol_SE',
        templateUrl: 'views/analyse/section_E.html'
        //controller: 'ficheVadListCTRL'
    };
    var Enrol_SF = {
        name: 'enrol_SF',
        url: '/enrol_SF',
        templateUrl: 'views/analyse/section_F.html'
        //controller: 'ficheVadListCTRL'
    };
    var Enrol_SG = {
        name: 'enrol_SG',
        url: '/enrol_SG',
        templateUrl: 'views/analyse/section_G.html'
        //controller: 'ficheVadListCTRL'
    };
    var Enrol_SH = {
        name: 'enrol_SH',
        url: '/enrol_SH',
        templateUrl: 'views/analyse/section_H.html'
        //controller: 'ficheVadListCTRL'
    };
    var Enrol_SIJK = {
        name: 'enrol_SIJK',
        url: '/enrol_SIJK',
        templateUrl: 'views/analyse/section_I,J,K.html'
        //controller: 'ficheVadListCTRL'
    };
    $stateProvider.state(accueil);
    $stateProvider.state(dossier);
    $stateProvider.state(dossierList);
    $stateProvider.state(groupe);
    $stateProvider.state(groupeList);
    $stateProvider.state(reference);
    $stateProvider.state(referenceList);
    $stateProvider.state(vad);
    $stateProvider.state(vadList);
    $stateProvider.state(Enrol_List);
    $stateProvider.state(Enrol_SA);
    $stateProvider.state(Enrol_SB);
    $stateProvider.state(Enrol_SC);
    $stateProvider.state(Enrol_SD);
    $stateProvider.state(Enrol_SE);
    $stateProvider.state(Enrol_SF);
    $stateProvider.state(Enrol_SG);
    $stateProvider.state(Enrol_SH);
    $stateProvider.state(Enrol_SIJK);

}]);

saisie.run(['$rootScope','$http', function ($rootScope,$http) {
    console.log("entrer dans run");
    console.log("entrer dans location = ",location);
    console.log("entrer dans location.host = ",location.host);
    var hre = location.href;
    serverAdresse = hre.substring(0,hre.indexOf("saisie/index.html"));
    console.log("entrer dans serverAdresse = ",serverAdresse);
    $rootScope.baseUrl = serverAdresse;
    var userUrl = serverAdresse+"user/username/";
    var organisationUrl = serverAdresse+"organisation";
    var programmeUrl = serverAdresse+"programme";
    var elementUrl = serverAdresse+"element";
    var ensembleOptionUrl = serverAdresse+"ensembleOption";
    var meUrl = serverAdresse+"me";
    var exit = serverAdresse+"login?logout";

    var allOrgUnit = [];
    var meDataView = [];
    var ensembleOption = [];
    var element = [];
    var Me = {};


    $rootScope.lesProgrammes =[];
    $rootScope.organisation =[];
    $rootScope.element =[];
    $rootScope.useURL = "";
    $rootScope.orgUnitSelect = {};
    $rootScope.programmeSelect = {};
    $rootScope.Me = {};
    $rootScope.user = {};
    $rootScope.afficheArbre = true;
    $rootScope.lesProgrammesEnrolement = {};

    $rootScope.arbrePret = false;
    $rootScope.ActivteApel = "";

    getMe();
    
    $rootScope.initSaisie = function () {
        getMe();
    };

    function getMe() {
        $http.get(meUrl).then(function (response) {
           // console.log("getMe() > response = ",response);
            if (response) {
                Me = response.data;
                $rootScope.Me = Me;
                gestRole();
                getMetaData();
            }
        }, function (err) {
            console.log(err);
        });
    };

    $rootScope.getMe = function getMe() {
        getMe();
    };

    //getMetaData();
    function getMetaData() {
        getEnsembleOption();
    }

    function getEnsembleOption() {
        //console.log("getEnsembleOption()");
        $http.get(ensembleOptionUrl).then(function (response) {
            //console.log("ensembleOptionUrl ==>",response);
            if (response) {
                ensembleOption = response.data;
                getElement();
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getElement() {
        //console.log("getElement()");
        $http.get(elementUrl).then(function (response) {
           // console.log("elementUrl ==>",response);
            if (response) {
                element = response.data;
                gestionElement();
                getProgramme();
                getOrganisation();
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getProgramme() {
        //console.log("getProgramme()");
        $http.get(programmeUrl).then(function (response) {
            //  console.log("programmeUrl ==>",response);
            if (response) {
                $rootScope.lesProgrammes = response.data;
                gestionProgramme();
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getOrganisation() {
       // console.log("getOrganisation()");
        $http.get(organisationUrl).then(function (response) {
           // console.log("organisationUrl ==>",response);
            if (response) {
                $rootScope.organisation = response.data;
                allOrgUnit = angular.copy($rootScope.organisation);
                MeOrgName();
                retourActivite();

            }
        }, function (err) {
            console.log(err);
        });
    }

    function gestionProgramme() {
       // console.log("gestionProgramme()");
        for (var l = 0; l < $rootScope.lesProgrammes.length; l++) {
            if($rootScope.lesProgrammes[l].elements && $rootScope.lesProgrammes[l].elements.length != 0){
                for(var i = 0; i< $rootScope.lesProgrammes[l].elements.length;i++){
                    if($rootScope.lesProgrammes[l].elements[i].element && $rootScope.lesProgrammes[l].elements[i].element != null){
                        for (var j = 0; j < element.length; j++) {
                            if ($rootScope.lesProgrammes[l].elements[i].element == element[j].id) {
                                $rootScope.lesProgrammes[l].elements[i].element = {};
                                $rootScope.lesProgrammes[l].elements[i].element = element[j];
                                break;
                            }
                        }
                    }
                }

            }
        }

      //  console.log("gestionProgramme() > $rootScope.lesProgrammes = ",$rootScope.lesProgrammes)
    }

    function gestionElement() {
        //console.log("gestionElement()");
        for (var l = 0; l < element.length; l++) {
            if(element[l].ensembleOption && element[l].ensembleOption != null){
                for (var j = 0; j < ensembleOption.length; j++) {
                    if (element[l].ensembleOption.id == ensembleOption[j].id) {
                        element[l].ensembleOption = ensembleOption[j];
                        break;
                    }
                }
            }
        }
    }

    function MeOrgName() {
        //console.log("MeOrgName");
        meDataView = angular.copy(Me.organisations);
        var a = 0, b = meDataView.length;
        while (a < b) {
            for (var i = 0, j = allOrgUnit.length; i < j; i++) {
                if (meDataView[a].id === allOrgUnit[i].id) {
                    meDataView[a] = allOrgUnit[i];
                    meDataView[a].children = allOrgUnit[i].childrens;
                    delete meDataView[a].childrens;
                    if (meDataView[a].children.length > 0) {
                        meDataView[a].collapse = true;
                        meDataView[a].children = getChildren(meDataView[a].children);
                    }
                    break;
                }
            }
            a++;
        }
        $rootScope.arbre = meDataView;
        $rootScope.arbrePret = true;
    }


    function getChildren(child) {
        //console.log("getChildren");
        var a = 0, b = child.length;
        while (a < b) {
            for (var i = 0, j = allOrgUnit.length; i < j; i++) {
                if (child[a].id === allOrgUnit[i].id) {
                    child[a] = allOrgUnit[i];
                    child[a].children = allOrgUnit[i].childrens;
                    delete child[a].childrens;
                    if (child[a].children.length > 0) {
                        child[a].children = getChildren(child[a].children);
                    }
                    break;
                }
            }
            a++;
        }
        return child;
    }

    $rootScope.toutes_les_donnees = function (lesSelects) {
        console.log("entrer dans $rootScope.toutes_les_données");
        console.log(lesSelects);
        $rootScope.orgUnitSelect = lesSelects;
    };

    $rootScope.getOrgSelect = function (orgId) {

        for(var i= 0;i<allOrgUnit.length;i++){
            if(allOrgUnit[i].id == orgId){
                $rootScope.orgUnitSelect = allOrgUnit[i];
            }
        }
    };

    function gestRole() {
        $rootScope.role = {};

        for(var i = 0;i<Me.roleDefinis.length;i++){
            $rootScope.role[Me.roleDefinis[i]] = true;
        }
    }
    
    function retourActivite() {
        if($rootScope.ActivteApel == "VADLIST"){
            $rootScope.lancerVADList();
        }
    }
    $rootScope.logout = function () {
        window.location = exit;
    }
}]);