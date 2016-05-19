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

    this.cancelTrip = function(trip) {
      $log.debug('FlightDelayController.cancelTrip-init')
      flightService
        .cancelTrip(trip.tripId)
        .then(() => $state.reload())
        // check for errors ???

      // this.message = 'Your Trip has been canceled!'

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

      // this.message = 'Your Trip has been canceled!'

    }



  }

})();
