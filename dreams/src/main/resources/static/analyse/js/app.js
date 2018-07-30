
var serverAdresse = "";

var analyse = angular.module('analyse', ['ui.router','tree_directive']);
analyse.config(['$stateProvider','$urlRouterProvider', function ($stateProvider,$urlRouterProvider) {
    var accueil = {
        name: 'accueil',
        url: '/accueil',
        templateUrl: 'views/accueil.html'
       // controller: 'accueilCTRL'
    };
    var enrol = {
        name: 'enrolement',
        url: '/enrolement',
        templateUrl: 'views/enrolement.html',
        controller: 'enrolementCTRL'
    };
    var eligibilite = {
        name: 'eligibilite',
        url: '/eligibilite',
        templateUrl: 'views/eligibilite.html'
        //controller: 'eligibiliteCTRL'
    };
    var besoin = {
        name: 'besoinBeneficiare',
        url: '/besoinBeneficiare',
        templateUrl: 'views/evaluationBesoin.html',
        controller: 'evaluationBesoinCTRL'
    };
    var vad = {
        name: 'vad',
        url: '/vad',
        templateUrl: 'views/vad.html',
        controller: 'vadCTRL'
    };
    var reference = {
        name: 'reference',
        url: '/reference',
        templateUrl: 'views/reference.html',
        controller: 'referenceCTRL'
    };
    var groupe = {
        name: 'groupe',
        url: '/groupe',
        templateUrl: 'views/groupe.html',
        controller: 'groupeCTRL'
    };
    var rapport = {
        name: 'rapport',
        url: '/rapport',
        templateUrl: 'views/rapport.html',
        controller: 'rapportCTRL'
    };

    $stateProvider.state(accueil);
    $stateProvider.state(enrol);
    $stateProvider.state(besoin);
    $stateProvider.state(eligibilite);
    $stateProvider.state(vad);
    $stateProvider.state(reference);
    $stateProvider.state(groupe);
    $stateProvider.state(rapport);

}]);
analyse.run(['$rootScope','$http','$stateParams','$state','$filter','$timeout','$window','Excel', function ($rootScope,$http,$stateParams,$state,$filter,$timeout,$window,Excel) {
    console.log("entrer dans run");
    var hre = location.href;
    serverAdresse = hre.substring(0,hre.indexOf("analyse/index.html"));
    console.log("entrer dans serverAdresse = ",serverAdresse);
    var organisationUrl = serverAdresse+"organisation";
    var programmeUrl = serverAdresse+"programme";
    var elementUrl = serverAdresse+"element";
    var ensembleOptionUrl = serverAdresse+"ensembleOption";
    var meUrl = serverAdresse+"me";
    var exit = serverAdresse+"login?logout";
    $rootScope.dataValueUrl = serverAdresse+"dataValue/analyse";
    $rootScope.baseUrl = serverAdresse;
    var etat = "jour";
    var Me = {};
    var meDataView = [];
    var allOrgUnit = [];
    var ensembleOption = [];
    var element = [];
    var codeProg;
    var periodeSelect = {};
    var periodActu = [];
    var lesIndicateurs = [];
    var lesIndicateursDetails = [];
    var excelFeuileName = null;
    $rootScope.programme =[];
    $rootScope.organisation =[];
    $rootScope.element =[];
    $rootScope.Me = {};
    $rootScope.progSelected = {};
    $rootScope.periode = {};
    $rootScope.trim = {};
    $rootScope.mois = {};
    $rootScope.inter = {};
    $rootScope.lejour = null;
    $rootScope.collectIndicateurs =[];
    $rootScope.collectIndicateursSeclect =[];

    
    $rootScope.afficheOrg = false;
    var exeJour = false;
    var exeInter = false;
    var ans = $filter('date')(new Date(), 'yyyy');

    var dataMensuel = [{debut: '01-01',fin: '31-01', name: 'Janvier'}, {debut: '01-02',fin: '29-02', name: 'Fevrier'}, {
        debut: '01-03',fin: '31-03', name: 'Mars'}, {debut: '01-04',fin: '30-04', name: 'Avril'}, {debut: '01-05',fin: '30-05', name: 'Mai'},
        {debut: '01-06',fin: '30-06', name: 'Juin'}, {debut: '01-07',fin: '31-07', name: 'Juillet'}, {debut: '01-08',fin: '31-08', name: 'Aout'},
        {debut: '01-09',fin: '30-09', name: 'Septembre'}, {debut: '01-10',fin: '31-10', name: 'Octobre'},
        {debut: '01-11',fin: '30-11', name: 'Novembre'}, {debut: '01-12',fin: '31-12', name: 'Decembre'}];

    var dataTrimestre = [{debut: '01-01',fin: '31-03', name: 'Janvier - Mars'}, 
        {debut: '01-04',fin: '30-06', name: 'Avril - Juin'}, 
        {debut: '01-07',fin: '30-09', name: 'Juillet - Septembre'}, 
        {debut: '01-10',fin: '31-12', name: 'Octobre - Decembre'}];

    var mois = [{code: "01",name: "Janvier"},{code: "02",name: "Février"},{code: "03",name: "Mars"},{code: "04",name: "Avril"},
        {code: "05",name: "Mai"},{code: "06",name: "Juin"},{code: "07",name: "Juillet"},{code: "08",name: "Août"},
        {code: "09",name: "Septembre"},{code: "10",name: "Octobre"},{code: "11",name: "Novembre"},{code: "12",name: "Décembre"}];

    var Trimestre = [{code: "T1",name: "Janvier - Mars"},{code: "T2",name: "Avril - Juin"},
        {code: "T3",name: "Juillet - Septembre"},{code: "T4",name: "Octobre - Décembre"}];

    $rootScope.typePeriode = [{id: "mois",name:"Mensuel"},{id: "trimestre",name: "Trimestriel"}];
    $rootScope.ListPeriode = [];
    $rootScope.periodeSelect = [];
    $rootScope.laperiode = "mois";
    $rootScope.trimestre = false;
    $rootScope.mois = false;
    $rootScope.interval = false;
    $rootScope.jour = true;
    $rootScope.analyseRapport = true;
    $rootScope.annee = $filter('date')(new Date(), "yyyy");
    getPeriode(mois,$rootScope.annee );
    $rootScope.annee = parseInt($rootScope.annee , 10);
    var lan = angular.copy($rootScope.annee);


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
    
    getMe();

    function getMe() {
        $http.get(meUrl).then(function (response) {
            console.log("getMe() > response = ",response);
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
    function gestRole() {
        $rootScope.role = {};

        for(var i = 0;i<Me.roleDefinis.length;i++){
            $rootScope.role[Me.roleDefinis[i]] = true;
        }
    }

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
        console.log("getElement()");
        $http.get(elementUrl).then(function (response) {
            // console.log("elementUrl ==>",response);
            if (response) {
                element = response.data;
                gestionElement();
                getProgramme();
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getProgramme() {
        //console.log("getProgramme()");
        $http.get(programmeUrl).then(function (response) {
            console.log("programmeUrl ==>",response);
            if (response) {
                $rootScope.lesProgrammes = response.data;
                $rootScope.programme = getProgramValide(response.data);
                gestionProgramme();
                getOrganisation();
            }
        }, function (err) {
            console.log(err);
        });
    }

    function getProgramValide(prog) {
        console.log("prog = ",prog);
        var pro = [];
        for(var i =0;i<prog.length;i++){
            if(prog[i].code != "rapport"){
                pro.push(prog[i]);
            }
        }
        return pro;
    }
    function getOrganisation() {
        // console.log("getOrganisation()");
        $http.get(organisationUrl).then(function (response) {
            // console.log("organisationUrl ==>",response);
            if (response) {
                $rootScope.organisation = response.data;
                allOrgUnit = angular.copy($rootScope.organisation);
                MeOrgName();

            }
        }, function (err) {
            console.log(err);
        });
    }

    function gestionProgramme() {
        //console.log("gestionProgramme() $rootScope.lesProgrammes = ",$rootScope.lesProgrammes);
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
            if($rootScope.lesProgrammes[l].code == "rapport"){
                gestIndicateurProgramme($rootScope.lesProgrammes[l]);
            }
        }

       // console.log("gestionProgramme() > $rootScope.lesProgrammes = ",$rootScope.lesProgrammes)
    }

    function gestIndicateurProgramme(prog) {
        console.log("gestIndicateurProgramme() > prog = ",prog);
        lesIndicateurs = [];
        lesIndicateursDetails = [];
        for(var i = 0;i<prog.elements.length;i++){
            var tmp = {};
            tmp.id = prog.elements[i].element.id;
            tmp.name = prog.elements[i].element.name;
            lesIndicateurs.push(tmp);
            lesIndicateursDetails.push(tmp);
            if(prog.elements[i].element.ensembleOption && prog.elements[i].element.ensembleOption.options && prog.elements[i].element.ensembleOption.options.length != 0){
                for(var j = 0;j<prog.elements[i].element.ensembleOption.options.length;j++){
                    var tmp = {};
                    tmp.id = prog.elements[i].element.id +"."+prog.elements[i].element.ensembleOption.options[j].id;
                    tmp.name = prog.elements[i].element.name+" - "+prog.elements[i].element.ensembleOption.options[j].name;
                    lesIndicateursDetails.push(tmp);
                }

            }
        }
        $rootScope.collectIndicateurs = angular.copy(lesIndicateurs);

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
        initial();
    }
    function getChildren(child) {
        console.log("getChildren");
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

    $rootScope.listArbre = function () {
        $rootScope.afficheOrg = true;
        $rootScope.orgUnitSelect = {};
    };

    $rootScope.organisationSelected = function (org) {
        console.log("organisationSelected() org = ",org);
        $rootScope.orgUnitSelect = org;
        $rootScope.afficheOrg = false;
    };

    $rootScope.prev = function (ou,anne) {
        console.info("enter in prev, anne ",anne);
        if(ou == 'debut'){
            anDebut -= 1;
            $scope.periodDebut = periodeLance(anDebut);
        }else{
            anFin -= 1;
            $scope.periodFin = periodeLance(anFin);
        }
    };

    $rootScope.next = function (ou) {


    };

    function periodeLance(an,periode) {
        //console.log("enter in periodeLance");
       // console.log("enter in periodeLance, an = ",an," periode = ",periode);
        var peri = [];
        for(var i = 0; i < periode.length;i++){
            var temp = {};
            temp.debut = periode[i].debut+'-'+an;
            temp.name = periode[i].name+" "+an;
            temp.fin = periode[i].fin+'-'+an;

            peri.push(temp);
        }
        console.log("peri = ",angular.copy(peri));
        periodActu = peri;
        return peri;
    }
    function getPeriodActu(debut){
        console.log("getPeriodActu() debut: ",debut," // periodActu = ",periodActu);
        for(var i = 0; i < periodActu.length;i++){
            if(debut == periodActu[i].debut){
                return periodActu[i];
            }
        }
    }

    $rootScope.selectTremestre = function () {
        if(etat != "trimestre"){
            etat = "trimestre";
            $timeout( function(){
                $rootScope.$apply(function() {
                    $rootScope.trimestre = true;
                    $rootScope.mois = false;
                    $rootScope.interval = false;
                    $rootScope.jour = false;
                    $rootScope.rapportPeriod = false;
                    initial();
                });
            },10);
            $rootScope.periodTrimestre = periodeLance(ans,dataTrimestre);
        }
    };
    $rootScope.selectMois = function () {
        if(etat != "mois") {
            etat = "mois";
            $timeout(function () {
                $rootScope.$apply(function () {
                    $rootScope.trimestre = false;
                    $rootScope.mois = true;
                    $rootScope.interval = false;
                    $rootScope.jour = false;
                    $rootScope.rapportPeriod = false;
                    initial();
                });
            }, 10);
            $rootScope.periodMois = periodeLance(ans,dataMensuel);
        }
    };
    $rootScope.selectInterval = function () {
        if(etat != "interval") {
            etat = "interval";
            $timeout(function () {
                $rootScope.$apply(function () {
                    $rootScope.trimestre = false;
                    $rootScope.mois = false;
                    $rootScope.interval = true;
                    $rootScope.jour = false;
                    $rootScope.rapportPeriod = false;
                    initial();
                });
            }, 10);
        }
    };
    $rootScope.selectJour = function () {
        if(etat != "jour") {
            etat = "jour";
            $timeout(function () {
                $rootScope.$apply(function () {
                    $rootScope.trimestre = false;
                    $rootScope.mois = false;
                    $rootScope.interval = false;
                    $rootScope.jour = true;
                    $rootScope.rapportPeriod = false;
                    initial();
                });
            }, 10);
        }

    };

    $rootScope.changeTrimestre = function(debut){
        exeJour = false;$rootScope.inter = {};
        $rootScope.periode = {};
        $rootScope.periode = getPeriodActu(debut);
        console.log("changeTrimestre() $rootScope.periode = ",$rootScope.periode);
    };
    $rootScope.changeMois = function(debut){
        exeJour = false;
        $rootScope.inter = {};
        $rootScope.periode = {};
        $rootScope.periode = getPeriodActu(debut);
        console.log("changeMois() $rootScope.periode = ",$rootScope.periode);
    };
    $rootScope.changeInterval = function(type, value){
        exeJour = false;
        if(!exeInter){
            $rootScope.periode = {};
            exeInter = true;
        }
        $rootScope.periode[type] = value;
        console.log("changeInterval() $rootScope.periode = ",$rootScope.periode);
    };
    $rootScope.changeJour = function(jour){
        $rootScope.inter = {};
        if(!exeJour){
            $rootScope.periode = {};
            exeJour = true;
        }
        $rootScope.periode.debut = jour;
        $rootScope.periode.fin = jour;
        console.log("changeJour() $rootScope.periode = ",$rootScope.periode);
    };

    $rootScope.execution = function () {
        console.log("execution ()");
        console.log("$rootScope.periode = ",$rootScope.periode);
        if($rootScope.analyseRapport){
            if($rootScope.collectIndicateursSeclect.length != 0 && $rootScope.periodeSelect.length != 0 && $rootScope.orgUnitSelect.id){
                $state.go('accueil');
                $timeout(function () {
                    $state.go('rapport');
                },100);
            }
        }else if($rootScope.orgUnitSelect.id && $rootScope.periode.debut && $rootScope.periode.fin){
           $state.go('accueil');
           $timeout(function () {
               collectOrganisation();
               $state.go(codeProg);
           },100);
       }
    };

    $rootScope.programmeClick = function(prog){
        codeProg = prog.code;
        excelFeuileName = prog.name
    };
    
    $rootScope.logout = function () {
        window.location = exit;
    };

    function collectOrganisation() {
        $rootScope.listOrg = [];
        $rootScope.listOrg.push($rootScope.orgUnitSelect.id);
        if($rootScope.orgUnitSelect.children.length != 0){
            getOrg($rootScope.orgUnitSelect.children)
        }
        console.log("$rootScope.listOrg = ",$rootScope.listOrg);
    }

    function getOrg(children) {
        for(var i = 0;i<children.length;i++){
            $rootScope.listOrg.push(children[i].id);
            if(children[i].children && children[i].children.length != 0){
                getOrg(children[i].children);
            }
        }
    }
    $rootScope.exportToExcel=function(tableId){ // ex: '#my-table'
        var exportHref=Excel.tableToExcel(tableId,excelFeuileName);
        $timeout(function(){location.href=exportHref;},100); // trigger download
    };

    $rootScope.showDetails = function (check) {
        console.log("showDetails  check = ",check);
        $rootScope.collectIndicateurs =[];
        $rootScope.collectIndicateursSeclect =[];
        if(check){
            $rootScope.collectIndicateurs = angular.copy(lesIndicateursDetails);
        }else{
            $rootScope.collectIndicateurs = angular.copy(lesIndicateurs);
        }
    };

    $rootScope.SelectIndicateur = function (indicateur, index) {
        $rootScope.collectIndicateursSeclect.push(indicateur);
        $rootScope.collectIndicateurs.splice(index,1);
    };
    $rootScope.indicateurSelected = function (indicateur, index) {
        $rootScope.collectIndicateurs.push(indicateur);
        $rootScope.collectIndicateursSeclect.splice(index,1);
    };

    $rootScope.voletRapport = function () {
        if($rootScope.analyseRapport){
            $rootScope.analyseRapport = false;
            $rootScope.jour = true;
        }else{
            $rootScope.analyseRapport = true;
            $rootScope.jour = false;
        }
        initial();
    };
    $rootScope.voletActivite = function () {
        if($rootScope.analyseRapport){
            $rootScope.analyseRapport = false;
            $rootScope.jour = true;
        }else{
            $rootScope.analyseRapport = true;
            $rootScope.jour = false;
        }
        initial();
    };

    function getPeriode(type,year) {
        $rootScope.ListPeriode = [];
        for(var i =0;i<type.length;i++){
            var tmp = {};
            tmp.id = year+type[i].code;
            tmp.name = type[i].name+" "+year;
            $rootScope.ListPeriode.push(tmp);
        }
        console.log("getPeriode(); $rootScope.ListPeriode = ",$rootScope.ListPeriode);
    }
    $rootScope.anneePreced = function (id) {
        lan--;
        if(id == "mois"){
            getPeriode(mois,lan );
        }
        if(id == "trimestre"){
            getPeriode(Trimestre,lan );
        }
    };
    $rootScope.anneeSuivant = function (id) {
        lan++;
        if(id == "mois"){
            getPeriode(mois,lan );
        }
        if(id == "trimestre"){
            getPeriode(Trimestre,lan );
        }
    };
    $rootScope.periodeSelected = function (id, year) {
        console.log("id = ", id);
        lan = year;
        $rootScope.periodeSelect = [];
        if(id == "mois"){
            getPeriode(mois,$rootScope.annee );
        }
        if(id == "trimestre"){
            getPeriode(Trimestre,$rootScope.annee );
        }
    };
    $rootScope.periodeSelectionner = function (periode,index) {
        $rootScope.periodeSelect.push(periode);
        $rootScope.ListPeriode.splice(index,1);
    };
    $rootScope.deletePeriode = function (periode,index) {
        $rootScope.ListPeriode.push(periode);
        $rootScope.periodeSelect.splice(index,1);
    };
}]);