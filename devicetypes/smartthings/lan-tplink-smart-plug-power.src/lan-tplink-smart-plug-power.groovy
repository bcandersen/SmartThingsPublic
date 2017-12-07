//THIS FILE IS PART OF THE NEW SUPER CONNECT APP FEATURE.
/**
 *  LAN TP-Link Smart Plug Power
 *
 *  TP-Link Smart Smart Plug Power
 *
 *  Author: SmartThings
 */
metadata {
	definition (name: "LAN TP Link Smart Plug Power", namespace: "smartthings", author: "SmartThings", ocfDeviceType: "oic.d.smartplug", runLocally: true, minHubCoreVersion: '000.019.00012', executeCommandsLocally: true) {
		capability "Actuator"
		capability "Switch"
		capability "Sensor"
		capability "Power Meter"
		capability "Polling"
		capability "Refresh"
		capability "Health Check"

		command "subscribe"
		command "resubscribe"
		command "unsubscribe"
	}

	// simulator metadata
	simulator {}

	// UI tile definitions
	tiles(scale: 2) {
		multiAttributeTile(name: "switch", type: "lighting", width: 6, height: 4, canChangeIcon: true) {
			tileAttribute("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label: 'On', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#00A0DC", nextState: "turningOff"
				attributeState "off", label: 'Off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff", nextState: "turningOn"
				attributeState "turningOn", label: 'Turning On', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#00A0DC", nextState: "turningOff"
				attributeState "turningOff", label: 'Turning Off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff", nextState: "turningOn"
			}
			tileAttribute("power", key: "SECONDARY_CONTROL") {
				attributeState "power", label: '${currentValue} W'
			}
		}

		standardTile("refresh", "device.power", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
			state "default", label: '', action: "refresh.refresh", icon: "st.secondary.refresh"
		}

		main "switch"
		details(["switch", "refresh"])
	}
}
