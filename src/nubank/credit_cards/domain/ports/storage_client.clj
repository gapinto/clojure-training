(ns nubank.credit-cards.domain.ports.storage-client)

(defprotocol StorageClient
  "Protocol for simple storage mechanism"
  (read-all [storage] "Return the entire contents of storage")
  (put! [storage update-fn] "Mutate the storage with the provided function"))
