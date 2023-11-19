package guru.qa.rococo.service;

import guru.qa.rococo.model.CurrencyValues;
import guru.qa.rococo.model.DataFilterValues;
import guru.qa.rococo.model.StatisticJson;
import guru.qa.rococo.service.api.RestContentClient;
import guru.qa.rococo.service.api.UserDataClient;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticAggregator {

    private final RestContentClient restSpendClient;
    private final UserDataClient userDataClient;

    @Autowired
    public StatisticAggregator(RestContentClient restContentClient, UserDataClient userDataClient) {
        this.restSpendClient = restContentClient;
        this.userDataClient = userDataClient;
    }

//    public @Nonnull
//    List<StatisticJson> enrichStatisticRequest(@Nonnull String username,
//                                               @Nullable CurrencyValues filterCurrency,
//                                               @Nullable DataFilterValues filterPeriod) {
//        CurrencyValues userDefaultCurrency = userDataClient.currentUser(username).getCurrency();
//        return restSpendClient.statistic(username, userDefaultCurrency, filterCurrency, filterPeriod);
//    }
}
