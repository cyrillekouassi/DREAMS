analyse.controller('accueilCTRL', ['$scope', '$rootScope', '$stateParams', '$http', function ($scope, $rootScope, $stateParams, $http) {
    console.log("entrer dans accueilCTRL");
    var user = $stateParams.user;
    console.log("$stateParams = ", $stateParams);
    console.log("user = " + user);

    var userUrl = "/user/username/";
    var organisationUrl = "/organisation";
    var programmeUrl = "/programme";
    var elementUrl = "/element";
    var ensembleOptionUrl = "/ensembleOption";
    var optionUrl = "/option";
    var allOrgUnit = [];
    var meDataView = [];
    $rootScope.user = {};
    
    $rootScope.afficheArbre = true;


    getUser();
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
                getMetaData();
            }
        }, function (err) {
            console.log(err);
        });
    }
    function fixOrg() {
        // fixé temporairement l'organisation à cote d'ivoire
        meDataView = [];
        var tp = {}; tp.id = "TngugI9URPK";
        meDataView.push(tp);
        console.log("MeOrgName() tp = ",tp);
        console.log("MeOrgName() meDataView = ",meDataView);
    }

    //getMetaData();
    function getMetaData() {
        $http.get(programmeUrl).then(function (response) {
            console.log("programmeUrl ==>",response);
            if (response) {
                $rootScope.programme = response.data;
            }
        }, function (err) {
            console.log(err);
        });

        $http.get(organisationUrl).then(function (response) {
            console.log("organisationUrl ==>",response);
            if (response) {
                $rootScope.organisation = response.data;
                allOrgUnit = angular.copy($rootScope.organisation);
                MeOrgName();
            }
        }, function (err) {
            console.log(err);
        });

        $http.get(elementUrl).then(function (response) {
            console.log("elementUrl ==>",response);
            if (response) {
                $rootScope.element = response.data;
            }
        }, function (err) {
            console.log(err);
        });
        $http.get(ensembleOptionUrl).then(function (response) {
            console.log("ensembleOptionUrl ==>",response);
            if (response) {
                $rootScope.ensembleOption = response.data;
            }
        }, function (err) {
            console.log(err);
        });
        $http.get(optionUrl).then(function (response) {
            console.log("optionUrl ==>",response);
            if (response) {
                $rootScope.option = response.data;
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
    }
}]);