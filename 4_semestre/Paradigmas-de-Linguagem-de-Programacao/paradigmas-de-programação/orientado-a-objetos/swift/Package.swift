import PackageDescription

let pacoteDoSistemaBancario = Package(
    name: "SistemaBancarioOrientadoAObjetos",
    targets: [
        .executableTarget(
            name: "SistemaBancarioOrientadoAObjetos",
            path: "Sources/SistemaBancarioOrientadoAObjetos"
        )
    ]
)
