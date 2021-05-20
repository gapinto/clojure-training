(ns nubank.credit-cards.domain.ports.storage-client)

(defprotocol StorageClient
  "Protocol for simple storage mechanism"
  (credit-cards [storage] "Return the entire contents of storage")
  (add-credit-card! [storage credit-card] "Mutate the storage with the provided function"))
