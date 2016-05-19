'use strict';

(() => {
  angular
    .module('flights.flight')
    .controller('FlightDelayController', FlightDelayController);

  FlightDelayController.$inject = ['delayedFlightList', 'flightService', '$state', '$stateParams', '$log'];

  function FlightDelayController(delayedFlightList, flightService, $state, $stateParams, $log) {
    $log.debug('FlightDelayController-init')

    // on Html
    this.delayedFlightList = delayedFlightList
    this.message

    this.cancelTrip = function() {
      $log.debug('FlightDelayController.cancelTrip-init')
      this.trip = flightService.getDelayTrip()
      flightService
        .cancelTrip(this.trip.tripId)
        .then(() => $state.go('tripList'))
        // check for errors ???

      // this.message = 'Your Trip has been canceled!'

    }

    this.checkAltTrips = function() {
      $log.debug('FlightDelayController.checkAltTrips-init')
      // this.trip = flightService.getDelayTrip()
      // flightService
      //   .cancelTrip(this.trip.tripId)
      //   .then(() => $state.go('tripList'))

    }

    this.flightStatus = function(flight) {
      // $log.debug('FlightDelayController.flightStatus-init')
      if (flight.flightStatus == 'A') {
        return 'Flight has Arrived'
      } else {
        if (flight.flightStatus == 'D') {
          return 'Flight is Delayed'
        } else {
          return 'Flight is on schedule'
        }
      }
    }



  }

})();
