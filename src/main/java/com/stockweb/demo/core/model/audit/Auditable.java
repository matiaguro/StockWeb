package com.stockweb.demo.core.model.audit;

public interface Auditable {
    Audit getAudit();

    void setAudit(Audit audit);
}
