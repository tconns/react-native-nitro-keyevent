import Foundation

class HybridNitroKeyEvent: HybridNitroKeyEventSpec {

    private var onKeyDownCallback: (([String: Any]) -> Void)?
    private var onKeyUpCallback: (([String: Any]) -> Void)?

    @objc func onKeyDown(_ callback: @escaping ([String: Any]) -> Void) {
        onKeyDownCallback = callback
    }

    @objc func onKeyUp(_ callback: @escaping ([String: Any]) -> Void) {
        onKeyUpCallback = callback
    }

    // Gọi hàm này khi native detect key down
    func handleKeyDown(keyCode: Int, pressedKey: String, action: Int, repeatCount: Int?) {
        onKeyDownCallback?(["keyCode": keyCode, "pressedKey": character, "action": action, "repeatCount": repeatCount as Any])
    }

    // Gọi hàm này khi native detect key up
    func handleKeyUp(keyCode: Int, pressedKey: String, action: Int) {
        onKeyUpCallback?(["keyCode": keyCode, "pressedKey": pressedKey, "action": action])
    }
}