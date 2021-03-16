package com.silamoney.client.util;

import java.util.logging.Level;

public interface Logger {
  default void log(Level level, Object message){
  }

  default void log(Level level, Object message, Throwable error) {
  }
}
