package org.example.storageorchestrator;

public interface Orchestrator {
    void setNext(Orchestrator orchestrator);
    void handle(OrchestratorUtility.Request request);
}
