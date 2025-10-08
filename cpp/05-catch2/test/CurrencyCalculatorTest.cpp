#include <catch2/catch_test_macros.hpp>
#include "../src/CurrencyCalculator.hpp"
#include "../src/RateService.hpp"
#include <memory>

// Simple implementation of RateService for testing
class TestRateService : public RateService {
public:
    std::optional<double> getRate(const std::string& currency) override {
        if (currency == "CNY") return 7.20;
        if (currency == "EUR") return 0.85;
        return std::nullopt;
    }
};

TEST_CASE("CurrencyCalculator tests", "[CurrencyCalculator]") {
    auto rateService = std::make_shared<TestRateService>();
    CurrencyCalculator sut(rateService);

    SECTION("testRunWithValidInput") {
        Money result = sut.convert(100.00, "CNY", "EUR");
        REQUIRE(result.getAmount() == 11.81); // Rounded
        REQUIRE(result.getCurrency() == "EUR");
    }
}
