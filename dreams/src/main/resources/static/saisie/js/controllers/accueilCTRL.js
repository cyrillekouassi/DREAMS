saisie.controller('accueilCTRL', ['$scope', '$rootScope', '$stateParams', '$http', function ($scope, $rootScope, $stateParams, $http) {
    console.log("entrer dans accueilCTRL");
    var user = $stateParams.user;
    console.log("$stateParams = ", $stateParams);
    console.log("user = " + user);

    var userUrl = "/user/username/";
    var organisationUrl = "/organisation";
    var programmeUrl = "/programme";
    var elementUrl = "/element";
    var ensembleOptionUrl = "/ensembleOption";
    //var optionUrl = "/option";
    var allOrgUnit = [];
    var meDataView = [];
    var ensembleOption = [];
    var element = [];
    $rootScope.user = {};
    $rootScope.afficheArbre = true;
    $rootScope.programmeEnrolement = {};


    getUser();
    getMetaData();
    function getMetaData() {
        getEnsembleOption();
    }
    
    function getUser() {
        if ($stateParams.user) {
            userUrl = userUrl + $stateParams.user;
            $rootScope.useURL = $stateParams.user;
        }
        $http.get(userUrl).then(function (response) {
            console.log(response);
            if (response) {
                meDataView = response.data.organisations;
                $rootScope.user = response.data;
                console.log("meDataView = ", meDataView);
                //getMetaData();
            }
        }, function (err) {
            console.log(err);
        });
    }

    
    

    function MeOrgName() {
        //console.log("MeOrgName");
        fixOrg();
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
        console.log("meDataView ==>");
        console.log(meDataView);
        $rootScope.arbre = meDataView;
    }
    function fixOrg() {
        // fixé temporairement l'organisation à cote d'ivoire
        meDataView = [];
        var tp = {}; tp.id = "TngugI9URPK";
        meDataView.push(tp);
        console.log("MeOrgName() tp = ",tp);
        console.log("MeOrgName() meDataView = ",meDataView);
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

    $rootScope.toutes_les_donnees = function (lesSelects) {
        console.log("entrer dans $rootScope.toutes_les_données");
        console.log(lesSelects);
        $rootScope.orgUnitSelect = lesSelects;
    };

    function gestEnrolement() {
        $rootScope.enrolementElement = [];
        for (var i = 0; i < $rootScope.programme.length; i++) {
            if ($rootScope.programme[i].code == "enrolement") {
                $rootScope.programmeEnrolement = $rootScope.programme[i];
                break;
            }
        }

        for (var l = 0, k = $rootScope.programmeEnrolement.elements.length; l < k; l++) {
            for (var j = 0; j < $rootScope.element.length; j++) {
                if ($rootScope.element[j].id == $rootScope.programmeEnrolement.elements[l].element) {
                    $rootScope.enrolementElement.push($rootScope.element[j]);
                    break;
                }
            }
        }
    }
    
    

}]);