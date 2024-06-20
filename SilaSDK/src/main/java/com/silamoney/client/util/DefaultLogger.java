package com.silamoney.client.util;

import java.util.logging.Level;

public class DefaultLogger implements Logger {

  private final java.util.logging.Logger log;
  public DefaultLogger(Class clazz) {
    this.log = java.util.logging.Logger.getLogger(clazz.getName());
  }
  @Override
  public void log(Level level, Object message) {
    log.log(level, String.valueOf(message));
  }

  @Override
  public void log(Level level, Object message, Throwable error) {
    log.log(level, String.valueOf(message), error);
  }
}
