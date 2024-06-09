package com.komikive.cloud;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;


/**
 * 自定义等级访问，按照会员等级访问
 */
@Component
public class LevelRoutePredicateFactory extends AbstractRoutePredicateFactory<LevelRoutePredicateFactory.Config> {


	public LevelRoutePredicateFactory() {
		super(Config.class);
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Collections.singletonList("userType");
	}

	@Override
	public Predicate<ServerWebExchange> apply(LevelRoutePredicateFactory.Config config) {
		return serverWebExchange -> {
			String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
			return config.userType.equals(userType);
		};
	}

	@Validated
	public static class Config{
		@Setter@Getter@NotEmpty
		private String userType;

	}
}
