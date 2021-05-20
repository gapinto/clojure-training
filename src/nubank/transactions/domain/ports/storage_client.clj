(ns nubank.transactions.domain.ports.storage-client)

(defprotocol StorageClient
  "Protocol for simple storage mechanism"
  (transactions [storage] "Return the entire contents of storage")
  (add-transaction! [storage transaction] "Mutate the storage with the provided function"))
