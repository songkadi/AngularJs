<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link href="webjars/bootstrap/3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-fluid" ng-app="ClaimApp">
        <div class="row" ng-controller="ClaimEditor">
            <div class="col-md-12">
                <form role="form" class="form-inline" ng-submit="submit()" name="claimForm">
                    <div class="form-group">
                        <label for="claimNumber">
                            Claim Number
                        </label>
                        <input type="text" ng-model="claim.claimNumber" class="form-control" id="claimNumber" />
                    </div>
                    <div class="form-group">
                        <label for="carVehicle">
                            Car Vehicle
                        </label>
                        <input type="text" ng-model="claim.carVehicle" class="form-control" id="carVehicle" />
                    </div>
                    <div class="form-group">
                        <label for="latitude">
                            Latitude
                        </label>
                        <input type="number" step="any" ng-model="claim.latitude" class="form-control" id="latitude" />
                    </div>
                    <div class="form-group">
                        <label for="longitude">
                            Longitude
                        </label>
                        <input type="number" step="any" ng-model="claim.longitude" class="form-control" id="longitude" />
                    </div>
                    <input type="submit" class="btn btn-primary" value="Submit" />
                </form>
            </div>
        </div>
        <hr/>
        <div class="row h-100">
            <div class="col-md-4" ng-controller="ClaimList">
                <div class="col-md-12" ng-repeat="claim in claims">
                    <input type="radio" ng-click="clickClaim(claim)" name="radio" value="{{claim.claimNumber}}"> <a id='modal-{{$index}}' href="#modal-container-{{$index}}" role="button" class="btn" data-toggle="modal">
						<span class="badge badge-default">{{claim.claimNumber}}</span>
					</a>
                    <div class="modal fade" id="modal-container-{{$index}}" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="myModalLabel">
										Claim Details
									</h5>
                                    <button type="button" class="close" data-dismiss="modal">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    {{claim.claimNumber}} : {{claim.carVehicle}} : {{claim.latitude}} : {{claim.longitude}}
                                </div>
                                <div class="modal-footer">
                                    <button type="button" ng-click="editClaim(claim)" class="btn btn-primary">
                                        Edit
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8" ng-controller="ClaimShowMap">
                <div class="media">
                    <label class="control-label col-md-3" for="claimNumber">claimNumber:{{claim.claimNumber}}</label>
                    <label class="control-label col-md-3" for="carVehicle">carVehicle:{{claim.carVehicle}}</label>
                    <label class="control-label col-md-3" for="latitude">latitude:{{claim.latitude}}</label>
                    <label class="control-label col-md-3" for="longitude">longitude:{{claim.longitude}}</label>
                </div>
            </div>
        </div>
    </div>
    <script src="webjars/angularjs/1.6.5/angular.min.js"></script>
    <script src="webjars/jquery/3.3.1/dist/jquery.min.js"></script>
    <script src="webjars/tether/1.4.3/dist/js/tether.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="js/app.js"></script>
    <script src="js/controller/claimController.js"></script>
    <script src="js/service/claimService.js"></script>
</body>

</html>