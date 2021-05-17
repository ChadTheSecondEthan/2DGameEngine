package Entity;

public interface Action {
	void onAction();

	interface OnDestroyListener extends Action {}
	static OnDestroyListener onDestroy(OnDestroyListener a) { return a; }

	interface OnMouseClickListener extends Action {}
	static OnMouseClickListener onMouseClick(OnMouseClickListener a) { return a; }

	interface OnMouseEnterListener extends Action {}
	static OnMouseEnterListener onMouseEnter(OnMouseEnterListener a) { return a; }

	interface OnMouseExitListener extends Action {}
	static OnMouseExitListener onMouseExit(OnMouseExitListener a) { return a; }
}
