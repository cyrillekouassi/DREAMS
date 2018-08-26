saisie.controller('enrol_SG_Ctrl', ['$scope', '$rootScope', '$stateParams', '$http','$filter','$state', function ($scope, $rootScope, $stateParams, $http,$filter,$state) {
    console.log("entrer dans enrol_SG_Ctrl");
    var ValueUrl = serverAdresse+'dataValue';
	var dataInstanceEntete = {};
    var dataInstance = {};
    dataInstanceEntete.programme = $stateParams.prog;
    dataInstanceEntete.organisation = $stateParams.org;
    dataInstanceEntete.instance = $stateParams.inst;
    dataInstance = angular.copy(dataInstanceEntete);
	$scope.enrolG = {};
var enrolSectionG = ["_01_humil_public",
	"_01_a_frequence",
	"_02_menace_nuire",
	"_02_a_frequence",
	"_03_injure",
	"_03_a_frequence",
	"_04_menace_prive",
	"_04_a_frequence",
	"_05_porte_main",
	"_05_a_frequence",
	"_06_viol",
	"_06_a_frequence",
	"_07_abus_sexuel",
	"_07_a_frequence",
	"_08_demande_aide",
	"_09_aupris_de_qui",
	"_09_a_autre_aupris_de_qui",
	"_10_secours",
	"_10_a_autre_secours"];
	
	mappigData();
	
	
	 function mappigData() {
	        for(var i = 0;i<enrolSectionC.length;i++){
	            var id = getElementId(enrolSectionG[i]);
	            if(id){
	                for(var j=0;j<$rootScope.enrolementData.length;j++){
	                    if(id == $rootScope.enrolementData[j].element){
	                        $scope.enrolG[enrolSectionG[i]] = $rootScope.enrolementData[j].value;
	                    }
	                }
	            }
	        }
	        console.log("mappigData() $scope.enrolG = ",$scope.enrolG);
	    }
	
	$scope.savePage = function (){
		console.log("entrer dans savePage");
		console.log("$scope.enrolG = ",$scope.enrolG);
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
		$state.go('enrol_SF',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
		
	}
	
	function getElement() {
        console.log("getElement()");
        $scope.enrolG._09_aupris_de_qui = gestaupris();
        $scope.enrolG._10_secours = gestsecours();
        console.log("$scope.enrolG = ",$scope.enrolG);
        for(var pop in $scope.enrolG){
            if($scope.enrolG[pop] != null && $scope.enrolG[pop] && $scope.enrolG[pop] != ""){
                var id = getElementId(pop);
                if(id){
                    var data = {};
                    data.element = id;
                    data.value = $scope.enrolG[pop];
                    data.numero = 1;
                    dataInstance.dataValue.push(data);
                }else{
                    console.error("getElement(). Element non trouvé, code = ",pop);
                }
            }else {
                console.info("getElement(). Element sans valeur, code = ",pop," // valeur = ",$scope.enrolG[pop]);
            }
        }
        console.log("dataInstance = ",dataInstance);

    }
	
	function gestaupris(){
		console.log("$scope.aupris = ",$scope.aupris);
		var auprisValue=null;
		 for(var pop in $scope.aupris){
			 if($scope.aupris[pop]){
				if(!auprisValue){
					auprisValue= "" + pop;
				} 
				else{
					auprisValue= auprisValue +" " + pop;
				}
			 }
		 }
		 console.log("auprisValue = ",auprisValue);
		 return auprisValue;
	}
	
	function gestsecours(){
		console.log("$scope.secours = ",$scope.secours);
		var secoursValue=null;
		 for(var pop in $scope.secours){
			 if($scope.secours[pop]){
				if(!secoursValue){
					secoursValue= "" + pop;
				} 
				else{
					secoursValue= secoursValue +" " + pop;
				}
			 }
		 }
		 console.log("secoursValue = ",secoursValue);
		 return secoursValue;
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
        $state.go('enrol_SH',{org: $rootScope.orgUnitSelect.id, prog: dataInstance.programme, inst: dataInstance.instance});
    }
    
    
    
}]);