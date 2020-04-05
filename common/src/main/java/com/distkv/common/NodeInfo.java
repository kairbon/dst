package com.distkv.common;

import com.distkv.common.id.NodeId;

import java.io.Serializable;

public class NodeInfo implements Serializable {
  /**
   * Whether this node is a master.
   * <p>
   * True if this node is master node, false if this node is a slave node.
   */
  private boolean isMaster;

  private static final long serialVersionUID = -4220017786527146673L;

  private NodeId nodeId;

  private String address;

  private long lastTimeHeartbeat;

  private NodeStatus status;

  public static Builder newBuilder() {
    return new Builder();
  }

  private NodeInfo(Builder builder) {
    this.address = builder.getAddress();
    this.nodeId = builder.getNodeId();
    this.isMaster = builder.isMaster();
    this.status = NodeStatus.RUNNING;
    this.lastTimeHeartbeat = 0;
  }

  public NodeId getNodeId() {
    return nodeId;
  }

  public void setNodeId(NodeId nodeId) {
    this.nodeId = nodeId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Boolean isMaster() {
    return isMaster;
  }

  public void setIsMaster(Boolean master) {
    isMaster = master;
  }

  public NodeStatus getStatus() {
    return status;
  }

  public void setStatus(NodeStatus status) {
    this.status = status;
  }

  public long getLastTimeHeartbeat() {
    return lastTimeHeartbeat;
  }

  public void setLastTimeHeartbeat(long lastTimeHeartbeat) {
    this.lastTimeHeartbeat = lastTimeHeartbeat;
  }

  public static class Builder {
    private NodeId nodeId;

    private String address;

    private Boolean isMaster;

    public Boolean isMaster() {
      return isMaster;
    }

    public Builder setIsMaster(Boolean master) {
      isMaster = master;
      return this;
    }

    public NodeId getNodeId() {
      return nodeId;
    }

    public Builder setNodeId(NodeId nodeId) {
      this.nodeId = nodeId;
      return this;
    }

    public String getAddress() {
      return address;
    }

    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    public NodeInfo build() {
      return new NodeInfo(this);
    }

  }
}

