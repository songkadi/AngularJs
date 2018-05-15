claimApp.controller('ClaimEditor', ['$scope', 'ClaimService', function($scope, ClaimService) {
    $scope.claim = ClaimService.editedClaim;
    $scope.submit = ClaimService.saveClaim;
}]);

claimApp.controller('ClaimList', ['$scope', 'ClaimService', function($scope, ClaimService) {
    $scope.claims = ClaimService.allClaims;
    $scope.editClaim = ClaimService.editClaim;
    $scope.clickClaim = ClaimService.clickClaim;

    ClaimService.refreshClaims();
}]);

claimApp.controller('ClaimShowMap', ['$scope', 'ClaimService', function($scope, ClaimService) {
    $scope.claim = ClaimService.selectedClaim
}]);