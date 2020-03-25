package com.distkv.client;

import com.distkv.asyncclient.DefaultAsyncClient;
import com.distkv.asyncclient.DistkvAsyncClient;
import com.distkv.common.utils.FutureUtils;
import com.distkv.rpc.protobuf.generated.DistkvProtocol.DistkvResponse;

public class DefaultDistkvClient implements DistkvClient {

  private static final String typeCode = "X";

  private DistkvStringProxy stringProxy;

  private DistkvListProxy listProxy;

  private DistkvSetProxy setProxy;

  private DistkvDictProxy dictProxy;

  private DistkvSortedListProxy sortedListProxy;

  private DistkvIntProxy intProxy;


  /// The `DistkvSyncClient` is wrapped with a `DistkvAsyncClient`.
  private DistkvAsyncClient asyncClient;

  public DefaultDistkvClient(String serverAddress) {
    asyncClient = new DefaultAsyncClient(serverAddress);

    stringProxy = new DistkvStringProxy(asyncClient.strs());
    listProxy = new DistkvListProxy(asyncClient.lists());
    setProxy = new DistkvSetProxy(asyncClient.sets());
    dictProxy = new DistkvDictProxy(asyncClient.dicts());
    sortedListProxy = new DistkvSortedListProxy(asyncClient.sortedLists());
    intProxy = new DistkvIntProxy(asyncClient.ints());
  }

  @Override
  public boolean connect() {
    return true;
  }

  @Override
  public boolean isConnected() {
    return true;
  }

  @Override
  public boolean disconnect() {
    asyncClient.disconnect();
    return true;
  }

  @Override
  public DistkvStringProxy strs() {
    return stringProxy;
  }

  @Override
  public DistkvDictProxy dicts() {
    return dictProxy;
  }

  @Override
  public DistkvListProxy lists() {
    return listProxy;
  }

  @Override
  public DistkvSetProxy sets() {
    return setProxy;
  }

  @Override
  public DistkvSortedListProxy sortedLists() {
    return sortedListProxy;
  }

  @Override
  public DistkvIntProxy ints() {
    return intProxy;
  }

  @Override
  public void activeNamespace(String namespace) {
    asyncClient.activeNamespace(namespace);
  }

  @Override
  public void deactiveNamespace() {
    asyncClient.deactiveNamespace();
  }

  @Override
  public void drop(String key) {
    DistkvResponse response = FutureUtils.get(asyncClient.drop(key));
    CheckStatusUtil.checkStatus(response.getStatus(), key, typeCode);
  }

  @Override
  public void expire(String key, long expireTime) {
    DistkvResponse response = FutureUtils.get(asyncClient.expire(key, expireTime));
    CheckStatusUtil.checkStatus(response.getStatus(), key, typeCode);
  }

  @Override
  public String getActivedNamespace() {
    return asyncClient.getActivedNamespace();
  }

}
