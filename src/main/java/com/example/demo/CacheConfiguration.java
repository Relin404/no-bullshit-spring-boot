package com.example.demo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {
  // This class is used to enable caching and scheduling in the application.
  // The @EnableCaching annotation enables Spring's annotation-driven cache management capability.
  // The @EnableScheduling annotation enables Spring's scheduled task execution capability.

  @Bean
  public CacheManager cacheManager() {
    ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();
    manager.setAllowNullValues(false);
    manager.setCacheNames(List.of("productCache"));

    return manager;
  }

  @CacheEvict(value = "productCache", allEntries = true)
  @Scheduled(fixedDelay = 7000, initialDelay = 0)
  public void evictProductCache() {
    System.out.println("Evicting product cache");
    // This method is scheduled to run every 7 seconds and evicts all entries from the productCache.
    // This is useful for clearing the cache periodically to ensure that stale data is not served.
    // The initialDelay parameter specifies the delay before the first execution of the method.
  }
}
