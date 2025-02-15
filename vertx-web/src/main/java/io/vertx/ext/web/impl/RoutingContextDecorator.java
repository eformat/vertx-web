package io.vertx.ext.web.impl;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.*;
import io.vertx.ext.auth.User;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Decorate a {@link RoutingContext} and simply delegate all method calls to the decorated handler
 * 
 * @author <a href="mailto:stephane.bastian.dev@gmail.com>Stéphane Bastian</a>
 *
 */
public class RoutingContextDecorator implements RoutingContext {

  private final Route currentRoute;
  private final RoutingContext decoratedContext;
  
  public RoutingContextDecorator(Route currentRoute, RoutingContext decoratedContext) {
    Objects.requireNonNull(currentRoute);
    Objects.requireNonNull(decoratedContext);
    this.currentRoute = currentRoute;
    this.decoratedContext = decoratedContext;
  }

  @Override
  public int addBodyEndHandler(Handler<Void> handler) {
    return decoratedContext.addBodyEndHandler(handler);
  }

  @Override
  public RoutingContext addCookie(Cookie cookie) {
    return decoratedContext.addCookie(cookie);
  }

  @Override
  public int addHeadersEndHandler(Handler<Future> handler) {
    return decoratedContext.addHeadersEndHandler(handler);
  }

  @Override
  public int cookieCount() {
    return decoratedContext.cookieCount();
  }

  @Override
  public Set<Cookie> cookies() {
    return decoratedContext.cookies();
  }

  @Override
  public Route currentRoute() {
    return currentRoute;
  }

  @Override
  public Map<String, Object> data() {
    return decoratedContext.data();
  }

  @Override
  public void fail(int statusCode) {
    // make sure the fail handler run on the correct context
    vertx().runOnContext(future -> decoratedContext.fail(statusCode));
  }

  @Override
  public void fail(Throwable throwable) {
    // make sure the fail handler run on the correct context
    vertx().runOnContext(future -> decoratedContext.fail(throwable));
  }

  @Override
  public boolean failed() {
    return decoratedContext.failed();
  }

  @Override
  public Throwable failure() {
    return decoratedContext.failure();
  }

  @Override
  public Set<FileUpload> fileUploads() {
    return decoratedContext.fileUploads();
  }

  @Override
  public <T> T get(String key) {
    return decoratedContext.get(key);
  }

  @Override
  public String getAcceptableContentType() {
    return decoratedContext.getAcceptableContentType();
  }

  @Override
  public Buffer getBody() {
    return decoratedContext.getBody();
  }

  @Override
  public JsonObject getBodyAsJson() {
    return decoratedContext.getBodyAsJson();
  }

  @Override
  public String getBodyAsString() {
    return decoratedContext.getBodyAsString();
  }

  @Override
  public String getBodyAsString(String encoding) {
    return decoratedContext.getBodyAsString(encoding);
  }

  @Override
  public Cookie getCookie(String name) {
    return decoratedContext.getCookie(name);
  }

  @Override
  public String mountPoint() {
    return decoratedContext.mountPoint();
  }

  @Override
  public void next() {
    // make sure the next handler run on the correct context
    vertx().runOnContext(future -> decoratedContext.next());
  }

  @Override
  public String normalisedPath() {
    return decoratedContext.normalisedPath();
  }

  @Override
  public RoutingContext put(String key, Object obj) {
    return decoratedContext.put(key, obj);
  }

  @Override
  public boolean removeBodyEndHandler(int handlerID) {
    return decoratedContext.removeBodyEndHandler(handlerID);
  }

  @Override
  public Cookie removeCookie(String name) {
    return decoratedContext.removeCookie(name);
  }

  @Override
  public boolean removeHeadersEndHandler(int handlerID) {
    return decoratedContext.removeHeadersEndHandler(handlerID);
  }

  @Override
  public HttpServerRequest request() {
    return decoratedContext.request();
  }

  @Override
  public HttpServerResponse response() {
    return decoratedContext.response();
  }

  @Override
  public User user() {
    return decoratedContext.user();
  }

  @Override
  public Session session() {
    return decoratedContext.session();
  }

  @Override
  public void setAcceptableContentType(String contentType) {
    decoratedContext.setAcceptableContentType(contentType);
  }

  @Override
  public void reroute(HttpMethod method, String path) {
    decoratedContext.reroute(method, path);
  }

  @Override
  public void setBody(Buffer body) {
    decoratedContext.setBody(body);
  }

  @Override
  public void setSession(Session session) {
    decoratedContext.setSession(session);
  }

  @Override
  public void setUser(User user) {
    decoratedContext.setUser(user);
  }

  @Override
  public void clearUser() {
    decoratedContext.clearUser();
  }

  @Override
  public int statusCode() {
    return decoratedContext.statusCode();
  }

  @Override
  public Vertx vertx() {
    return decoratedContext.vertx();
  }
  
}
