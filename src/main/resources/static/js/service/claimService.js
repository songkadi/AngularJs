 claimApp.factory('ClaimService', ['URLs', '$http', function(URLs, $http) {

     var editedClaim = {};

     var allClaims = [];

     var selectedClaim = {};

     function saveClaim() {
         $http.post(URLs.claim, editedClaim).then(successSave, errorCall);

         if (editedClaim.claimNumber == selectedClaim.claimNumber) {
             clickClaim(editedClaim);
         }
     };

     function editClaim(claim) {
         editedClaim.claimNumber = claim.claimNumber;
         editedClaim.carVehicle = claim.carVehicle;
         editedClaim.latitude = claim.latitude;
         editedClaim.longitude = claim.longitude;
     };

     function refreshClaims() {
         $http.get(URLs.claim).then(successReload, errorCall);
     }

     function clickClaim(claim) {
         selectedClaim.claimNumber = claim.claimNumber;
         selectedClaim.carVehicle = claim.carVehicle;
         selectedClaim.latitude = claim.latitude;
         selectedClaim.longitude = claim.longitude;
     }

     function successReload(response) {
         allClaims.length = 0

         var getAll = response.data;
         for (i = 0; i < getAll.length; i++) {
             addToAllClaims(getAll[i]);
         }
     }

     function successSave() {
         console.log('Safe Claim successfully:' + JSON.stringify(editedClaim));
         // refreshClaims();
         addToAllClaims(editedClaim);
     }

     function errorCall() {
         console.log('Error Safe Claim:' + JSON.stringify(editedClaim));
     }

     function addToAllClaims(claim) {
         for (i = 0; i < allClaims.length; i++) {
             if (allClaims[i].claimNumber == claim.claimNumber) {
                 allClaims[i].carVehicle = claim.carVehicle;
                 allClaims[i].latitude = claim.latitude;
                 allClaims[i].longitude = claim.longitude;
                 return;
             }
         }

         allClaims.push({
             claimNumber: claim.claimNumber,
             carVehicle: claim.carVehicle,
             latitude: claim.latitude,
             longitude: claim.longitude
         });

         allClaims.sort(function(a, b) {
             var x = a.claimNumber;
             var y = b.claimNumber;
             return ((x.toUpperCase() > y.toUpperCase()) ? 1 : 0);
         });
     }

     return {
         editedClaim: editedClaim,
         allClaims: allClaims,
         selectedClaim: selectedClaim,
         saveClaim: saveClaim,
         editClaim: editClaim,
         refreshClaims: refreshClaims,
         clickClaim: clickClaim
     };
 }]);