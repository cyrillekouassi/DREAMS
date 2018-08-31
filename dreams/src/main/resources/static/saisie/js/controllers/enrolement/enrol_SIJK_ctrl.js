saisie.controller('enrol_SIJK_Ctrl', ['$scope', '$rootScope', '$stateParams', '$http','$filter','$state', function ($scope, $rootScope, $stateParams, $http,$filter,$state) {
    console.log("entrer dans enrol_SIJK_Ctrl");
    var ValueUrl = serverAdresse+'dataValue';
	var dataInstanceEntete = {};
    var dataInstance = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = angular.copy(dataInstanceEntete);
	$scope.enrolIJK = {};
	var enrolSectionIJK = ["_01_participation_program",
		"_01_a_autre_program",
		"_01_vie_assossiative",
		"_02_amis",
		"_03_reseau_social",
		"_04_person_confiance",
		"_05_group_epargn",
		"_06_refus_ide_group",
		"_07_trist_angoiss",
		"_01_nbre_repas",
		"_02_element_repas",
		"Comment"];
	
	mappigData();
	
	
	 function mappigData() {
	        for(var i = 0;i<enrolSectionIJK.length;i++){
	            var id = getElementId(enrolSectionIJK[i]);
	            if(id){
	                for(var j=0;j<$rootScope.enrolementData.length;j++){
	                    if(id == $rootScope.enrolementData[j].element){
	                        $scope.enrolIJK[enrolSectionIJK[i]] = $rootScope.enrolementData[j].value;
	                    }
	                }
	            }
	        }
	        console.log("mappigData() $scope.enrolIJK = ",$scope.enrolIJK);
	    }
	
	$scope.savePage = function (){
		console.log("entrer dans savePage");
		console.log("$scope.enrolIJK = ",$scope.enrolIJK);
		console.log("dataInstance = ",dataInstance);
		console.log("$rootScope.programmeSelect = ",$rootScope.programmeSelect);
		//dataInstance.dreamsId = $scope.enrolA.id_dreams;
        //dataInstance.dateActivite = $scope.enrolA.dat_enrol;
		dataInstance.dataValue = [];
		getElement();
		saveData();
		
	}
	
	$scope.previewPage = function (){
		console.log("entrer dans previewPage");
		$state.go('enrol_SH',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
		
	}
	
	function getElement() {
        console.log("getElement()");
        $scope.enrolIJK._01_participation_program = gestparticipation();
        $scope.enrolIJK._02_element_repas = gestrepas();
        console.log("$scope.enrolH = ",$scope.enrolIJK);
        for(var pop in $scope.enrolIJK){
            if($scope.enrolIJK[pop] != null && $scope.enrolIJK[pop] && $scope.enrolIJK[pop] != ""){
                var id = getElementId(pop);
                if(id){
                    var data = {};
                    data.element = id;
                    data.value = $scope.enrolIJK[pop];
                    data.numero = 1;
                    dataInstance.dataValue.push(data);
                }else{
                    console.error("getElement(). Element non trouvé, code = ",pop);
                }
            }else {
                console.info("getElement(). Element sans valeur, code = ",pop," // valeur = ",$scope.enrolIJK[pop]);
            }
        }
        console.log("dataInstance = ",dataInstance);

    }
	
	function gestparticipation(){
		console.log("$scope.participation = ",$scope.participation);
		var participationValue=null;
		 for(var pop in $scope.participation){
			 if($scope.participation[pop]){
				if(!participationValue){
					participationValue= "" + pop;
				} 
				else{
					participationValue= participationValue +" " + pop;
				}
			 }
		 }
		 console.log("participationValue = ",participationValue);
		 return participationValue;
	}
	
	function gestrepas(){
		console.log("$scope.repas = ",$scope.repas);
		var repasValue=null;
		 for(var pop in $scope.repas){
			 if($scope.repas[pop]){
				if(!repasValue){
					repasValue= "" + pop;
				} 
				else{
					repasValue= repasValue +" " + pop;
				}
			 }
		 }
		 console.log("repasValue = ",repasValue);
		 return repasValue;
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
	
	function succesSave() {
        $state.go('enrol_List',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
    }
    
    
    
}]);