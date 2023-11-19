package guru.qa.rococo.controller;

import guru.qa.rococo.service.StatisticAggregator;
import guru.qa.rococo.service.api.RestContentClient;
import guru.qa.rococo.service.api.UserDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    private final RestContentClient restContentClient;
    private final UserDataClient userDataClient;
    private final StatisticAggregator statisticAggregator;

    @Autowired
    public ArtistController(RestContentClient restContentClient, UserDataClient userDataClient, StatisticAggregator statisticAggregator) {
        this.restContentClient = restContentClient;
        this.userDataClient = userDataClient;
        this.statisticAggregator = statisticAggregator;
    }

//    @GetMapping("/spends")
//    public List<SpendJson> getSpends(@AuthenticationPrincipal Jwt principal,
//                                     @RequestParam(required = false) DataFilterValues filterPeriod,
//                                     @RequestParam(required = false) CurrencyValues filterCurrency) {
//        String username = principal.getClaim("sub");
//        return restContentClient.getSpends(username, filterPeriod, filterCurrency);
//    }
//
//    @PostMapping("/addSpend")
//    @ResponseStatus(HttpStatus.CREATED)
//    public SpendJson addSpend(@Valid @RequestBody SpendJson spend,
//                              @AuthenticationPrincipal Jwt principal) {
//        String username = principal.getClaim("sub");
//        CurrencyValues userCurrency = userDataClient.currentUser(username).getCurrency();
//        spend.setUsername(username);
//        spend.setCurrency(userCurrency);
//
//        return restContentClient.addSpend(spend);
//    }
//
//    @PatchMapping("/editSpend")
//    public SpendJson editSpend(@Valid @RequestBody SpendJson spend,
//                               @AuthenticationPrincipal Jwt principal) {
//        if (spend.getId() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id should be present");
//        }
//        String username = principal.getClaim("sub");
//        spend.setUsername(username);
//
//        return restContentClient.editSpend(spend);
//    }
//
//    @GetMapping("/statistic")
//    public List<StatisticJson> getTotalStatistic(@AuthenticationPrincipal Jwt principal,
//                                                 @RequestParam(required = false) CurrencyValues filterCurrency,
//                                                 @RequestParam(required = false) DataFilterValues filterPeriod) {
//        String username = principal.getClaim("sub");
//        return statisticAggregator.enrichStatisticRequest(username, filterCurrency, filterPeriod);
//    }
//
//    @DeleteMapping("/deleteSpends")
//    public ResponseEntity<Void> deleteSpends(@AuthenticationPrincipal Jwt principal,
//                                             @RequestParam List<String> ids) {
//        String username = principal.getClaim("sub");
//        return new ResponseEntity<>(restContentClient.deleteSpends(username, ids));
//    }
}
