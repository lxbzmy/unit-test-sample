#include <gtest/gtest.h>
#include <gmock/gmock.h>
#include "../src/CurrencyCalculator.hpp"
#include "../src/RateService.hpp"
#include "../src/Money.hpp"

// Mock class for RateService
class MockRateService : public RateService {
public:
    MOCK_METHOD(std::optional<double>, getRate, (const std::string& currency), (override));
};

class CurrencyCalculatorTest : public ::testing::Test {
protected:
    std::shared_ptr<MockRateService> mockRateService;
    std::shared_ptr<CurrencyCalculator> sut;

    void SetUp() override {
        mockRateService = std::make_shared<MockRateService>();
        sut = std::make_shared<CurrencyCalculator>(mockRateService);
    }
};

TEST_F(CurrencyCalculatorTest, testRunWithValidInput) {
    // given
    EXPECT_CALL(*mockRateService, getRate("CNY")).WillOnce(testing::Return(7.20));
    EXPECT_CALL(*mockRateService, getRate("EUR")).WillOnce(testing::Return(0.85));

    // when
    Money result = sut->convert(100.00, "CNY", "EUR");

    // then
    EXPECT_DOUBLE_EQ(11.81, result.getAmount());
    EXPECT_EQ("EUR", result.getCurrency());
}
