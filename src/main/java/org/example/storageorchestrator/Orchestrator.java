package org.example.storageorchestrator;

/**
 * Interface for orchestrator handling the chain
 *
 */
public interface Orchestrator {
    void setNext(Orchestrator orchestrator);
    void handle(OrchestratorUtility.Request request);
}
