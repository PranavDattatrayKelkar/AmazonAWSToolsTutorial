package org.example.storageorchestrator;

/**
 * Primary abstract clue used to define the chain and the setup to
 * point to the next handlers.
 *
 */
abstract class StorageOrchestrator implements Orchestrator {
    public Orchestrator next;

    @Override
    public void setNext(Orchestrator orchestrator) {
        this.next = orchestrator;
    }

    protected void forward(OrchestratorUtility.Request request) {
        if(next != null) {
            next.handle(request);
        }
    }
}
