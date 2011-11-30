package VEW.Planktonica2.DisplayEventHandlers;

public enum ButtonCommandNamesEnum {
	MOVE_UP, MOVE_DOWN,
	ADD_INSTANCE, REMOVE_INSTANCE, 
	RENAME_INSTANCE, TOP_GUN;
		

		
		/**
		 * 
		 * @param name
		 * @return Returns the enum of the string name or null if it does not exist
		 */
		public static ButtonCommandNamesEnum toButtonCommandNamesEnum(String name) {
			try {
				return ButtonCommandNamesEnum.valueOf(name);
			} catch (IllegalArgumentException e) {
				System.err.println("Could not find button enum in ButtonCommandNamesEnum list. " +
						"Does the enum exist?");
			} catch (NullPointerException e) {
				System.err.println("What is this... I don't even...");
			}
			return null;
		}
}
