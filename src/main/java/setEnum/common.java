package setEnum;

public enum common {

	INDEX{
		@Override
		public String toString(){
			return "index";

		}
	},
	VALUE{
		@Override
		public String toString(){
			return "value";

		}
	},
	VISIBLETEXT{
		@Override
		public String toString(){
			return "visibleText";

		}
	}
}