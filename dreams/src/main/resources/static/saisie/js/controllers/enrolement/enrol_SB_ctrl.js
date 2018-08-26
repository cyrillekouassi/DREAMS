// Declaration de la fonction du controleur
saisie.controller('enrol_SB_Ctrl', ['$scope', '$rootScope', '$stateParams', '$http','$filter','$state', function ($scope, $rootScope, $stateParams, $http,$filter,$state) {
    console.log("entrer dans enrol_SB_Ctrl");
	var ValueUrl = serverAdresse+'dataValue';
	var dataInstanceEntete = {};
    var dataInstance = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = angular.copy(dataInstanceEntete);
	$scope.enrolB = {};
	var enrolSectionB = ["_01_chef_menage","_01_a_autre_chef","_02_age_chef","_03_pere_vie","_04_mere_vie",
		"_05_parent_malad_chron","_06_type_logem","_06_a_autre_logem","_07_source_eau",
		"_07_a_autre_source_eau","_08_source_lumiere","_08_a_autre_source_lumiere","_09_type_toil",
		"_10_nbre_repas","_11_handicap","_11_a_autre_handicap","_12_a_nbre_adult_F","_12_b_nbre_adult_H",
		"_12_c_1_nbre_f_m10","_12_c_2_nbre_f_10_14","_12_c_3_nbre_f_15_19","_12_d_1_nbre_h_m10",
		"_12_d_2_nbre_h_10_14","_12_d_3_nbre_h_15_19","_12_nbre_mbre_menage"];
	
	mappigData();
	
	// Fonction pour afficher les valeurs dans bouton radios 
	 function mappigData() {
	        for(var i = 0;i<enrolSectionB.length;i++){
	            var id = getElementId(enrolSectionB[i]);
	            if(id){
	                for(var j=0;j<$rootScope.enrolementData.length;j++){
	                    if(id == $rootScope.enrolementData[j].element){
	                        $scope.enrolB[enrolSectionB[i]] = $rootScope.enrolementData[j].value;
	                    }
	                }
	            }
	        }
	        console.log("mappigData() $scope.enrolB = ",$scope.enrolB);
	    }
	
	// Fonction de sauvegarde des données de la page et passer a la page suivante
	$scope.savePage = function (){
		console.log("entrer dans savePage");
		console.log("$scope.enrolB = ",$scope.enrolB);
		console.log("dataInstance = ",dataInstance);
		console.log("$rootScope.programmeSelect = ",$rootScope.programmeSelect);
		//dataInstance.dreamsId = $scope.enrolA.id_dreams;
        //dataInstance.dateActivite = $scope.enrolA.dat_enrol;
		dataInstance.dataValue = [];
		getElement();
		saveData();
		
	}
	// Fonction de retour a la page précédente
	$scope.previewPage = function (){
		console.log("entrer dans previewPage");
		$state.go('enrol_SA',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
		
	}
	// Fonction d'envoi des données vers le serveur
	function getElement() {
        console.log("getElement()");
        $scope.enrolB._11_handicap = gesthandicap();
        console.log("$scope.enrolB = ",$scope.enrolB);
        for(var pop in $scope.enrolB){
            if($scope.enrolB[pop] != null && $scope.enrolB[pop] && $scope.enrolB[pop] != ""){
                var id = getElementId(pop);
                if(id){
                    var data = {};
                    data.element = id;
                    data.value = $scope.enrolB[pop];
                    data.numero = 1;
                    dataInstance.dataValue.push(data);
                }else{
                    console.error("getElement(). Element non trouvé, code = ",pop);
                }
            }else {
                console.info("getElement(). Element sans valeur, code = ",pop," // valeur = ",$scope.enrolB[pop]);
            }
        }
        console.log("dataInstance = ",dataInstance);

    }
	// Fonction de gestion des bottons checkbox
	function gesthandicap(){
		console.log("$scope.handicap = ",$scope.handicap);
		var handicapValue=null;
		 for(var pop in $scope.handicap){
			 if($scope.handicap[pop]){
				if(!handicapValue){
					handicapValue= "" + pop;
				} 
				else{
					handicapValue= handicapValue +" " + pop;
				}
			 }
		 }
		 console.log("handicapValue = ",handicapValue);
		 return handicapValue;
	}
	// Fonction de recupération des Id des elements de données pour les mettre dans le fichier json
	function getElementId(code) {
        for(var j = 0;j<$rootScope.programmeSelect.elements.length;j++){
            if($rootScope.programmeSelect.elements[j].element.code == code){
                return $rootScope.programmeSelect.elements[j].element.id;
            }
        }
        return null;
    }
	// Fonction de sauvegarde de donnée 
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
                //dataInstance.instance = succes.data.id;
                succesSave();
            }else{
                toastr["success"]("Echec d'enregistrement");
            }
        }, function(error){
            console.log("saveData() error = ",error);
            toastr["success"]("Echec d'enregistrement");
        });
    }
	// Fonction de enregistrement avec succès et passer a la page suivante
	function succesSave() {
        $state.go('enrol_SC',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
    }
	
	
}]);