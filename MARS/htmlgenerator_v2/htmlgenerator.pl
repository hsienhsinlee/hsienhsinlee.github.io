#!/usr/bin/perl

sub shellCmd {
	my $cmd = shift;
	system ( "$cmd\n" );
}

sub publicationByProject{
	my $targetHtmlFileName = shift;
	my $publicationManagerID = shift;

	if ($targetHtmlFileName == "3d") {
		shellCmd( "cat research-head-3d.html > temp.html" );
        }
	else {
		shellCmd( "cat research-head.html > temp.html" );i
	}
	shellCmd( "cat $targetHtmlFileName-head.html >> temp.html" );
	shellCmd( "cat $publicationManagerID.html >> temp.html" );
	shellCmd( "cat $targetHtmlFileName-tail.html >> temp.html" );
	shellCmd( "cat research-tail.html >> temp.html" );
	shellCmd( "cp temp.html ../research/$targetHtmlFileName.html" );
	shellCmd( "rm temp.html" );
	shellCmd( "rm $publicationManagerID.html" );

}

sub publicationByDate {
	my $targetHtmlFileName = shift;
	my $publicationManagerID = shift;
	my $title = shift;


	shellCmd( "sed -e \'s/$title/<b>$title<\\/b>/g\' publications-head.html > temp.html" );
	shellCmd( "cat $publicationManagerID.html >> temp.html" );
	shellCmd( "cat publications-tail.html >> temp.html" );
	shellCmd( "cp temp.html ../publications/$targetHtmlFileName.html" );
	shellCmd( "rm temp.html" );
	shellCmd( "rm $publicationManagerID.html" );

}

sub publicationByName {
	my $targetHtmlFileName = shift;
	my $publicationManagerID = shift;


	shellCmd( "cat people-head.html > temp.html" );
	shellCmd( "cat bio-$publicationManagerID.html >> temp.html" );
	shellCmd( "cat $publicationManagerID.html >> temp.html" );
	shellCmd( "cat people-tail.html >> temp.html" );
	shellCmd( "cp temp.html ../people/$targetHtmlFileName.html" );
	shellCmd( "rm temp.html" );
	shellCmd( "rm $publicationManagerID.html" );

}

sub publicationByField {

	my @types = ( "all", "journal", "conference", "workshop+poster", "bookchapters", "thesis" );
	my @fields = ( "all", "ml", "sustainability", "uarch", "secure", "embed", "lpower", "fpga", "soc", "parallel", "gfx" );

	my @type_title = (
				"All Papers",
				"Journal Articles",
				"Conference Papers",
				"Workshop and Poster",
				"Book Chapters",
				"Theses"
			);

	my @field_title = (
				"All Technical Papers (by date)",
				"Systems for Machine Learning",
				"Sustainability",
				"Conventional Processor Architecture and Compilers, Performance Modeling",
				"Secure, Dependable and Autonomic Computing, DRM",
				"Embedded Computing",
				"Low-Power Techniques",
				"FPGA Techniques",
				"3D ICs, SoC, Physical Design and EDA Tools",
				"Multicore, Parallel Architecture and Systems",
				"Support for 3D Graphics"
			);

	my $type_index = 0;
	my $field_index = 0;

	foreach( @types ) {
		$type = $_;

		$field_index = 0;

		foreach( @fields ) {
			$field = $_;

			my $targetHtmlFileName = $field . "_" . $type;
			my $publicationManagerID = $field . "_" . $type;

			shellCmd( "sed -e \'s/\(field\)/$field/g\' publications-head.html > temp-head0.html" );
			shellCmd( "sed -e \'s/\(type\)/$type/g\' temp-head0.html > temp-head1.html" );

			$ftitle = $field_title[ $field_index ];
			$ttitle = $type_title[ $type_index ];

			shellCmd( "sed -e \'s/$ftitle/<b>$ftitle<\\/b>/g\' temp-head1.html > temp-head2.html" );
			shellCmd( "sed -e \'s/$ttitle/<b>$ttitle<\\/b>/g\' temp-head2.html > temp-head3.html" );

			shellCmd( "sed -e \'s/tab_\($type\)/tab_front/g\' temp-head3.html > temp-head4.html" );
			shellCmd( "sed -e \'s/tab_\(.*\)/tab_back/g\' temp-head4.html > temp.html" );


			shellCmd( "cat $publicationManagerID.html >> temp.html" );
			shellCmd( "cat publications-tail.html >> temp.html" );
			shellCmd( "cp temp.html ../publications/$targetHtmlFileName.html" );
			shellCmd( "rm temp.html" );
			shellCmd( "rm temp-head*.html" );
			shellCmd( "rm $publicationManagerID.html" );

			$field_index = $field_index + 1;
		}

		$type_index = $type_index + 1;
	}


}

shellCmd( "javac PublicationManager.java" );
shellCmd( "java PublicationManager" );

publicationByProject( "research-secure", "research-sec" );
publicationByProject( "research-pod", "research-pod" );
publicationByProject( "research-multicore", "research-multicore" );
publicationByProject( "research-medical", "research-medical" );
publicationByProject( "research-introspective", "research-introspective" );
publicationByProject( "research-memory", "research-memory" );
publicationByProject( "3d", "3d" );

publicationByName( "Ahmad", "Ahmad" );
publicationByName( "Chinnak", "Chinnak" );
publicationByName( "Dong_Hyuk", "Dong_Hyuk" );
publicationByName( "Dean", "Dean" );
publicationByName( "Eric", "Eric" );
publicationByName( "Fayez", "Fayez" );
publicationByName( "Joonho", "Joonho" );
publicationByName( "Mrinmoy", "Mrinmoy" );
publicationByName( "Sungkap", "Sungkap" );
publicationByName( "Jen-Cheng", "Jen-Cheng" );
publicationByName( "Nak_Hee", "Nak_Hee" );
publicationByName( "Nishank", "Nishank" );
publicationByName( "Nob", "Nob" );
publicationByName( "Pratik", "Pratik" );
publicationByName( "Richard", "Richard" );
publicationByName( "Taeweon", "Taeweon" );
publicationByName( "Vikas", "Vikas" );
publicationByName( "Weidong", "Weidong" );
publicationByName( "Tzu-Wei", "Tzu-Wei" );
publicationByName( "Andrei", "Andrei" );
publicationByName( "Hossain", "Hossain" );
publicationByName( "Ali", "Ali" );
publicationByName( "Manoj", "Manoj" );
publicationByName( "Lifeng", "Lifeng" );
publicationByName( "Ilya", "Ilya" );
publicationByName( "Guanhao", "Guanhao" );

publicationByField();

#publicationByField( "all", "all", "All Technical Papers (by date)" );
#publicationByField( "uarch", "uarch", "Conventional Processor Architecture and Compilers, Performance Modeling" );
#publicationByField( "secure", "secure", "Secure, Dependable and Autonomic Computing, DRM" );
#publicationByField( "embed", "embed", "Embedded Computing" );
#publicationByField( "lpower", "lpower", "Low-Power Techniques" );
#publicationByField( "fpga", "fpga", "FPGA Techniques" );
#publicationByField( "soc", "soc", "3D ICs, SoC, Physical Design and EDA Tools" );
#publicationByField( "parallel", "parallel", "Multicore, Parallel Architecture and Systems" );
#publicationByField( "gfx", "gfx", "Support for 3D Graphics" );
