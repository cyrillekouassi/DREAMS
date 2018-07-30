conf.controller('rolesUserCTRL',['$scope','$http',function ($scope,$http) {
    console.log("entrer dans rolesUserCTRL");
    var urlRole = serverAdresse+"roleUser";
    var urlRoleDefine = serverAdresse+"roleDefinis";
    var definiChoisir = [];
    
    var allRolesUser = [];
    var allRolesDefinis = [];
    $scope.afficheSaisieRoles = false;
    $scope.afficheAjout = true;
    $scope.rolesUsers = [];
    $scope.rolesDefinis = [];
    $scope.rolesDefinies = [];
    $scope.role = {};
    
    $scope.afficheUserRoles = true;

    lancerRoleUser();

    function lancerRoleUser() {
        $http.get(urlRole).then(function (response) {
            console.log("User RoleUser ==>",response);
            allRolesUser = response.data;
            lancerRoleDefinis();
        }, function (err) {
            console.log(err);
        });
    }

    function lancerRoleDefinis() {
        $http.get(urlRoleDefine).then(function (response) {
            console.log("User RoleDefinis ==>",response);
            allRolesDefinis = response.data;
            initilialisation();
        }, function (err) {
            console.log(err);
        });

    }
    function initilialisation() {
        $scope.rolesUsers = allRolesUser;
        $scope.rolesDefinies = angular.copy(allRolesDefinis);
        console.log("$scope.rolesDefinies = ",$scope.rolesDefinies);
        $scope.rolesDefinis = angular.copy(allRolesDefinis);
        for(var i = 0;i<$scope.rolesDefinis.length;i++){
            $scope.rolesDefinis[i][i] = i;
        }
        console.log("$scope.rolesDefinis = ",$scope.rolesDefinis);
    }
    $scope.newRoles = function () {
        $scope.afficheSaisieRoles = true;
        $scope.afficheAjout = true;
    };
    $scope.autorise = function (id) {
      console.log("id = ",id);
        var existe = false;
        for(var i = 0;i<definiChoisir.length;i++){
            if(id === definiChoisir[i].id){
                definiChoisir.splice(i,1);
                existe = true;
            }
        }
        if(!existe){
            definiChoisir.push({"id": id})
        }
        console.log("definiChoisir = ",definiChoisir);

    };

    $scope.ajouterRoles = function () {
        console.log("ajouterRoles() role = ",$scope.role);
        $scope.role.rolesDefinies = definiChoisir;
        if($scope.role.name)
            saveRoleUser($scope.role);
    };

    function saveRoleUser(rol) {
        var url = serverAdresse+'roleUser';
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.post(url, rol, config).then(function(succes){
            console.log("saveRoleUser () succes = ",succes);
            sortir();
        }, function(error){
            console.log("saveRoleUser() error = ",error);
            sortir();
        });
    }

    function sortir() {
        $scope.role = {};
        definiChoisir = [];
        $scope.afficheSaisieRoles = false;
        $window.location.reload();
    }

    $scope.annuler = function () {
        $scope.role = {};
        definiChoisir = [];
        $scope.afficheSaisieRoles = false;
        for(var j=0;j<$scope.rolesDefinis.length;j++){
                $scope.rolesDefinis[j][j] = false;
        }
    };

    $scope.editRole = function (rol) {
        console.log("modifierRole() rol= ",rol);
        $scope.role = rol;
        definiChoisir = $scope.role.rolesDefinies;
        for(var i = 0;i<definiChoisir.length;i++){
            for(var j=0;j<$scope.rolesDefinis.length;j++){
                if(definiChoisir[i].id == $scope.rolesDefinis[j].id){
                    $scope.rolesDefinis[j][j] = true;
                    break;
                }
            }
        }
        $scope.afficheSaisieRoles = true;
        $scope.afficheAjout = false;
    };

    $scope.ModifierRoles = function () {
        console.log("ModifierRoles() role = ",$scope.role);
        $scope.role.rolesDefinies = definiChoisir;
        if($scope.role.name)
            updateRoleUser($scope.role.id,$scope.role);
    };
    function updateRoleUser(id,rol) {
        var url = serverAdresse+'roleUser/'+id;
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.put(url, rol, config).then(function(succes){
            console.log("updateRoleUser() succes = ",succes);
            sortir();
        }, function(error){
            sortir();
        });
    }
    
    $scope.newDefini = function () {
        $scope.afficheSaisieDefinie = true;
        $scope.afficheAjoutDefine = true;
    };

    $scope.editDefinies = function (autorise) {
        $scope.afficheSaisieDefinie = true;
        $scope.afficheAjoutDefine = false;
    }
}]);