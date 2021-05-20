(ns nubank.accounts.domain.ports.storage-client)

(defprotocol StorageClient
  "Protocol for simple storage mechanism"
  (accounts [storage] "Return the entire contents of storage")
  (add-account! [storage account] "Mutate the storage with the provided function"))
