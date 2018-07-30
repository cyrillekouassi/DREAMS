conf.controller('organisationCTRL',['$scope','$http','$window','$rootScope',function ($scope,$http,$window,$rootScope) {
    console.log("entrer dans organisationCTRL");
    $scope.affichesaisie = false;
    $scope.afficheAjout = false;
    var allOrgUnit = [];
    var meDataView = [];
    var leParent = {};
    $rootScope.arbre = [];

    var url = serverAdresse+"organisation";

    lancer();

    function lancer() {
        $http.get(url).then(function (response) {
            console.log("organisationUrl ==>",response);
            if (response) {
                //$rootScope.organisation = response.data;
                allOrgUnit = angular.copy(response.data);
                MeOrgName();
            }
        }, function (err) {
            console.log(err);
        });
    }

    function MeOrgName() {
        //console.log("MeOrgName");
        orgEntete();
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
    function orgEntete() {

        meDataView = [];
        for (var i = 0, j = allOrgUnit.length; i < j; i++) {
            if (allOrgUnit[i].level === 1) {
                var tp = {};
                tp.id = allOrgUnit[i].id;
                meDataView.push(tp);
                console.log("MeOrgName() tp = ",tp);
                //break;
            }
        }
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

    $scope.toutes_les_donnees = function (lesSelects) {
        console.log("entrer dans $rootScope.toutes_les_données");
        console.log(lesSelects);
        leParent = lesSelects;
        afficheEnfant(lesSelects);
    };
    function afficheEnfant(select) {
        console.log("afficheEnfant()");
        $scope.enfants = [];
        $scope.enfants = select.children;
        if(select.level <= 1){
            $scope.enfants.push(select);
        }
    }

    $scope.modifierOrg = function (unite) {
        console.log("unite = ",unite);
        $scope.afficheAjout = false;
        $scope.affichesaisie = true;
        $scope.org = angular.copy(unite);
        delete $scope.org.children;
        $scope.org.leparent = leParent.name;
    };
    $scope.appliqModif = function () {
        console.log("appliqModif > $scope.org = ",$scope.org);
        updateOrg($scope.org.id,$scope.org);
    };
    $scope.ajouterOrg = function () {
        console.log("ajouterOrg() debut > $scope.org = ",$scope.org);

        if($rootScope.arbre.length == 0){
            delete $scope.org.parent;
        }else {
            if(leParent){
                $scope.org.parent = { id: leParent.id};
            }
        }
        console.log("leParent = ",leParent);
        console.log("ajouterOrg() sortie > $scope.org = ",$scope.org);
        saveOrg($scope.org);


    };

    function saveOrg(org) {
        var url = serverAdresse+'organisation';
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(url, org, config).then(function(succes){
            console.log("saveOrg () succes = ",succes);
            sortir();
        }, function(error){
            console.log("saveOrg() error = ",error);
            sortir();
        });
    }
    function updateOrg(id,org) {
        var url = serverAdresse+'organisation/'+id;
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.put(url, org, config).then(function(succes){
            console.log("updateOrg() succes = ",succes);
            sortir();
        }, function(error){
            sortir();
        });
    }

    $scope.Ajouter = function () {
        console.log("Ajouter() = ");
        $scope.afficheAjout = true;
        $scope.affichesaisie = true;
        $scope.org = {};
        $scope.org.leparent = leParent.name;
    };

    function sortir() {
        lancer();
        $scope.affichesaisie = false;
        $scope.org = {};
        $window.location.reload();
    }
    $scope.annuler = function () {
        console.log("annuler() > $scope.org = ",$scope.org );
        $scope.affichesaisie = false;
        $scope.org = {};
    };
}]);