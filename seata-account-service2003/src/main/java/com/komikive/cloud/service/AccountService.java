package com.komikive.cloud.service;

import org.apache.ibatis.annotations.Param;

public interface AccountService {
	void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
