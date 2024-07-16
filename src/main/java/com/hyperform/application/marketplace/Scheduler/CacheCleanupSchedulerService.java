package com.hyperform.application.marketplace.Scheduler;

import com.hyperform.application.marketplace.model.response.CachedResponse;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheCleanupSchedulerService {

  private final Logger logger = LoggerFactory.getLogger(CacheCleanupSchedulerService.class);

  private final ConcurrentMap<String, CachedResponse<?>> responseCache;

  public CacheCleanupSchedulerService(ConcurrentMap<String, CachedResponse<?>> responseCache) {
    this.responseCache = responseCache;
  }

  @Scheduled(fixedRate = 60000)
  public void cleanupCache() {
    long now = System.currentTimeMillis();
    responseCache.entrySet().removeIf(entry -> entry.getValue().isExpired(now));
  }
}
