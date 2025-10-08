#pragma once

#include <string>
#include <optional>

class RateService {
public:
    virtual ~RateService() = default;
    virtual std::optional<double> getRate(const std::string& currency) = 0;
};
